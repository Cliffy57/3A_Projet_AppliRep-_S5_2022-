package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.Categories;
import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Magasin;
import fr.iut.serveur.modeles.Produit;
import javafx.scene.image.Image;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Semaphore;

import static fr.iut.serveur.modeles.Magasin.listeClient;

public class MagasinImpl extends UnicastRemoteObject implements MagasinInterface {

    Client current_user;    //Utilisateur du site
    private ArrayList<Magasin> ListeMagasin = new ArrayList<Magasin>();

    public MagasinImpl() throws RemoteException {
        Init();
    }

    private void Init()
    {
        listeClient.add(new Client("Mel@","123"));   //Ajout d'un client à la liste de client afin d'avoir une base pour bosser
        Magasin m0 = new Magasin("FLUNCH");
        Produit p1 = new Produit("APLI Étiquettes Ø 19mm 5 Feuilles 100 Pièces","Doté d'une experience de plus de 60 ans, APLI a pour objectif principal de satisfaire les utilisateurs pour leurs besoins dans le domaine du bureau, informatique, école et maison, industriel.","1.09","HUGO");
        Produit p2 = new Produit("Scotch Tape Transparent 12mm x 66m","Depuis des décennies, Scotch célèbre l'ingéniosité au quotidien en inventant des produits pour fixer, assembler, fabriquer et créer. Il existe une solution à chaque problème et pour chaque projet, il y a la marque Scotch.\n","2.39","Rock");
        Produit p3 = new Produit("COLOP Tampon Encreur Bleu 7x11cm","COLOP offre la possibilité de tamponner de manière permanente vos papiers","5.95","HUGO");
        //p1.
        //p2.setImg(new Image((Objects.requireNonNull(Produit.class.getResource("image/bag.jpg")).toString())));
        //p3.setImg(new Image((Objects.requireNonNull(Produit.class.getResource("image/bag.jpg")).toString())));
        //        p1.setCategorie("Metal"); p2.setCategorie("Rock"); p3.setCategorie("Metal");
        m0.AjouterProduitMagasin(p1);
        m0.AjouterProduitMagasin(p2);
        m0.AjouterProduitMagasin(p3);
        ListeMagasin.add(m0);

    }

    @Override
    public boolean CoClient(String mel,String nommagasin) throws RemoteException {
        if(vérificationclient(mel,nommagasin))
        {
            System.out.println("Client "+mel+" connecté");
            return true;
        }else return false;

    }

    @Override
    public void AjoutProduit(Produit p) throws RemoteException {
        current_user.AjouterPanier(p);
        System.out.println("Produit Ajouté pour le client:"+current_user.getMel());
    }

    @Override
    public void ConsulterPanier() throws RemoteException {
        current_user.ConsultePanier();
    }

    @Override
    public ArrayList<Produit> ConsulterListeProduitMagasin(String nommagasin) throws RemoteException {
        ArrayList <Produit> P = new ArrayList<>();
        for(Magasin m : ListeMagasin)
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
    private boolean vérificationclient(String mel,String nommagasin)
    {
        boolean trouve =false;
        for(Magasin m : ListeMagasin)
        {
            if(m.getNom().equals(nommagasin))    //Recherche le magasin dans la liste des magasins disponibles
            {
                for (Client cl: listeClient) {  //Recherche dans la liste client du magasin
                    if(cl.getMel().equals(mel))
                    {
                        trouve= true;
                        current_user = cl;
                    }
                }
            }

        }

        return trouve;

    }

    /**
     * AJoute un client au magasin
     * @param mail
     * @param motdepasse
     * @throws RemoteException
     */
    public void AjoutClient(String mail,String motdepasse) throws RemoteException
    {
        listeClient.add(new Client(mail,motdepasse));
    }

    /**
     * Calcule la somme du panier
     * @param cl
     * @throws RemoteException
     */
    @Override
    public double CalcSommeProduit(Client cl) throws RemoteException {
        if(cl != null)
        {
            return cl.CalculSommePanier();
        }else throw new IllegalArgumentException("Le client est null");
    }

    public Client RecupereClientActuel() throws RemoteException{
        return current_user;
    }
}

