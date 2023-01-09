package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public MagasinImpl(Client currentUser) throws RemoteException {
        this.currentUser = currentUser;
        this.nomMagasin = "shop";
        this.prices = new HashMap<>();
        this.mag = new Magasin(this.nomMagasin);
        listeMagasin.add(mag);
        Init();
    }

    private void Init()
    {

        //listeClient.add(new Client("Mel@","123"));   //Ajout d'un client à la liste de client afin d'avoir une base pour bosser
        //listeClient.add(new Client("hugo3@google.com","swage"));
        Magasin m0 = new Magasin("FLUNCH");
        Produit p1 = new Produit("APLI Étiquettes Ø 19mm 5 Feuilles 100 Pièces","Doté d'une experience de plus de 60 ans, APLI a pour objectif principal de satisfaire les utilisateurs pour leurs besoins dans le domaine du bureau, informatique, école et maison, industriel.","1.09","HUGO");
        Produit p2 = new Produit("Scotch Tape Transparent 12mm x 66m","Depuis des décennies, Scotch célèbre l'ingéniosité au quotidien en inventant des produits pour fixer, assembler, fabriquer et créer. Il existe une solution à chaque problème et pour chaque projet, il y a la marque Scotch.\n","2.39","Rock");
        Produit p3 = new Produit("COLOP Tampon Encreur Bleu 7x11cm","COLOP offre la possibilité de tamponner de manière permanente vos papiers","5.95","HUGO");
        //p1.
        //p2.setImg(new Image((Objects.requireNonNull(Produit.class.getResource("image/bag.jpg")).toString())));
        //p3.setImg(new Image((Objects.requireNonNull(Produit.class.getResource("image/bag.jpg")).toString())));
        //        p1.setCategorie("Metal"); p2.setCategorie("Rock"); p3.setCategorie("Metal");
        mag.ajouterProduitMagasin(p1);
        mag.ajouterProduitMagasin(p2);
       mag.ajouterProduitMagasin(p3);
        //System.out.println(currentUser.toString());
        //listeMagasin.add(mag);

    }

    @Override
    public boolean coClient(Client c) throws RemoteException {
       // if(vérificationclient(mel,nommagasin))
       // {
       // this.currentUser=c;
        this.currentUser = c;
        System.out.println(getCurrentUser().getUuid());
            System.out.println("Client  connecté"+c.getUuid()+"/"+c.getMel());
            return true;
        //}else return false;

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
                    System.out.println(p.getCategorie());
                    P.add(p);
                }
            }
        }
        return P;

    }

    //TODO Fonction à renforcer via mdp
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
/*
    private static final ThreadLocal<Client> currentClient = new ThreadLocal<>();

    public void setCurrentClient(Client client) {
        currentClient.set(client);
    }

    public Client recupereClientActuel() throws RemoteException{
        return currentClient.get();
    }

    //OU

    private Client currentClient;

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public Client recupereClientActuel() throws RemoteException {
        return currentClient;
    }*/
    @Override
    public Client recupereClientActuel() throws RemoteException{
        //System.out.println("RecupereToString");
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


    @Override
    public void addItem(String item, double price) {
        prices.put(item, price);
    }

    @Override
    public void addItem2(Produit p ) throws RemoteException {
        mag.ajouterProduitMagasin(p);
        System.out.println("Produit ajouté");
        System.out.println(mag.toString());
        System.out.println(mag.getListeProduits().toString());
    }
    public void setCurrentUser(Client currentUser) {
        this.currentUser = currentUser;
        System.out.println("Setter "+this.currentUser.getUuid());
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

