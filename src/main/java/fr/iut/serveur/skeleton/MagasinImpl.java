package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.*;
import fr.iut.serveur.modeles.outils.DataSet;
import fr.iut.serveur.modeles.outils.Ports;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static fr.iut.serveur.modeles.Magasin.listeClient;

public class MagasinImpl extends UnicastRemoteObject implements MagasinInterface {

    Client currentUser;    //Utilisateur du site
    private ArrayList<Magasin> listeMagasin = new ArrayList<Magasin>();
    private Map<String, Double> prices;
    private String nomMagasin;
    private Magasin mag;

    public String getNom() {
        return nomMagasin;
    }

    public MagasinImpl(String nom) throws RemoteException {
        this.currentUser = recupereClientActuel();
        this.nomMagasin = nom;
        this.prices = new HashMap<>();
        this.mag = new Magasin(this.nomMagasin);
        listeMagasin.add(mag);
        Init();
    }

    public MagasinImpl(Client currentUser,String nomDuMagasin) throws RemoteException {
        this.currentUser = currentUser;
        this.nomMagasin = nomDuMagasin;
        this.prices = new HashMap<>();
        this.mag = new Magasin(this.nomMagasin);
        listeMagasin.add(mag);
        Init();
    }
    private void Init()
    {

        Magasin m0 = new Magasin("FLUNCH");
       if(Objects.equals(mag.getNom(), "shop"))
       {
           DataSet.retourJeuDeDonnees_Magasin1(mag.getListeProduits());
       }else if(Objects.equals(mag.getNom(), "shop2"))
       {
           DataSet.retourJeuDeDonnees_Magasin2(mag.getListeProduits());
       }

    }

    @Override
    public boolean coClient(Client c) throws RemoteException {

        this.currentUser = c;
        System.out.println(getCurrentUser().getUuid());
            System.out.println("Client  connecté"+c.getUuid()+"/"+c.getMel());
            return true;


    }

    @Override
    public void ajoutProduit(Produit p) throws RemoteException {
        currentUser.AjouterPanier(p);
        System.out.println("Produit Ajouté pour le client:"+ currentUser.getMel()+"/"+currentUser.getUuid());
        System.out.println("Nb element impl="+currentUser.getPanier().size());
    }

    @Override
    public void consulterPanier() throws RemoteException {
        currentUser.ConsultePanier();
    }

    @Override
    public ArrayList<Produit> consulterListeProduitMagasin(String nommagasin) throws RemoteException {
        ArrayList <Produit> P = new ArrayList<>();
        for(Magasin m : listeMagasin)
        {

            if(m.getNom().equals(nommagasin))
            {
               ArrayList<Produit> listeProduits=  m.getListeProduits();
                for(Produit p : listeProduits)
                {
                    P.add(p);
                }
            }
        }
        return P;

    }

    /**
     * Vérifie si un client est présent dans la liste des clients
     * @param mel : Adresse mel du client
     */
    private boolean vérificationclient(String mel,String nomDuMagasin)
    {
        boolean trouveMagasin =false;
        for(Magasin m : listeMagasin)
        {
            if(m.getNom().equals(nomDuMagasin))    //Recherche le magasin dans la liste des magasins disponibles
            {
                for (Client cl: listeClient) {  //Recherche dans la liste client du magasin
                    if(cl.getMel().equals(mel))
                    {
                        trouveMagasin= true;
                        currentUser = cl;
                    }
                }
            }

        }

        return trouveMagasin;

    }

    /**
     * AJoute un client au magasin
     * @param mail
     * @param motdepasse
     * @throws RemoteException
     */
    public void ajoutClient(String mail, String motdepasse) throws RemoteException
    {
        listeClient.add(new Client(mail,motdepasse));
    }

    /**
     * Calcule la somme du panier
     * @param cl
     * @throws RemoteException
     */
    @Override
    public double calcSommeProduit(Client cl) throws RemoteException {
        if(cl != null)
        {
            return cl.CalculSommePanier();
        }else throw new IllegalArgumentException("Le client est null");
    }

    @Override
    public Client recupereClientActuel() throws RemoteException{
        return getCurrentUser();
    }
    @Override
    public double getPrice(String item) throws RemoteException {
        return prices.getOrDefault(item, 0.0);
    }

    @Override
    public void order(String item) throws RemoteException {
        if (!prices.containsKey(item)) {
            throw new RemoteException("Item not found: " + item);
        }
        // Process the order
        System.out.println("Order placed for item: " + item +" price :"+ getPrice(item));
    }

    @Override
    public String getnom() throws RemoteException {
        return nomMagasin;
    }
    @Override
    public boolean placeOrder(String clientUuid, double totalCost) throws RemoteException {
        System.out.println("Received order from client with UUID " + clientUuid + " for a total of " + totalCost + " euros");
        // Look up the client by their UUID
        Client client = getCurrentUser();
        System.out.println(client);
        // Send the order to the bank for processing
        if(sendOrderToBank(client, totalCost))
        {
            // Clear the client's cart
            client.clearCart();
            return true;
        };
    return false;
    }

    public void setCurrentUser(Client currentUser) {
        this.currentUser = currentUser;
    }
    public Client getCurrentUser() {
        return currentUser;
    }

    public Magasin getMagasin(){return mag;}

    public boolean sendOrderToBank(Client client, double totalCost) throws RemoteException{
        BanqueInterface bank = null;
        try {
            bank = (BanqueInterface) Naming.lookup("rmi://localhost:"+ Ports.Port_Banque+"/java");
        } catch (NotBoundException | MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // Send the client and total cost to the bank
        return bank.processOrder(client, totalCost);
    }
    public void orderConfirmed(Client client, double totalCost) throws RemoteException {
        // Update the shop's records
        System.out.println("Order confirmed on Shop side for client " + client.getUuid() + " for a total of " + totalCost + " euros");

        // Send a confirmation message to the client
        //ClientInterface clientStub = (ClientInterface) Naming.lookup("rmi://localhost/Client_" + client.getUuid());
        //clientStub.orderConfirmed(totalCost);
    }


}

