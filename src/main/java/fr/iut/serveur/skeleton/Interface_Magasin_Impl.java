package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Magasin;
import fr.iut.serveur.modeles.Produit;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import static fr.iut.serveur.modeles.Magasin.listeClient;

public class Interface_Magasin_Impl extends UnicastRemoteObject implements Interface_Magasin {

    Client current_user;    //Utilisateur du site
    private ArrayList<Magasin> ListeMagasin = new ArrayList<Magasin>();

    public Interface_Magasin_Impl() throws RemoteException {
    }

    private void Init()
    {
        listeClient.add(new Client("Mel@","123"));   //Ajout d'un client à la liste de client afin d'avoir une base pour bosser
        Magasin m0 = new Magasin("Flunch");
        Produit p1 = new Produit("APLI Étiquettes Ø 19mm 5 Feuilles 100 Pièces","Doté d'une experience de plus de 60 ans, APLI a pour objectif principal de satisfaire les utilisateurs pour leurs besoins dans le domaine du bureau, informatique, école et maison, industriel.","1.09");
        Produit p2 = new Produit("Scotch Tape Transparent 12mm x 66m","Depuis des décennies, Scotch célèbre l'ingéniosité au quotidien en inventant des produits pour fixer, assembler, fabriquer et créer. Il existe une solution à chaque problème et pour chaque projet, il y a la marque Scotch.\n","2.39");
        Produit p3 = new Produit("COLOP Tampon Encreur Bleu 7x11cm","COLOP offre la possibilité de tamponner de manière permanente vos papiers","5.95");

        m0.AjouterProduitMagasin(p1);
        m0.AjouterProduitMagasin(p2);
        m0.AjouterProduitMagasin(p3);
        ListeMagasin.add(m0);

    }

    @Override
    public void CoClient(String mel,String nommagasin) throws RemoteException {
        Init();
        vérificationclient(mel,nommagasin);
        System.out.println("Client "+mel+" connecté");
    }

    @Override
    public void AjoutProduit(Produit p) throws RemoteException {
        current_user.AjouterPanier(p);
    }

    @Override
    public void ConsulterPanier() throws RemoteException {
        current_user.ConsultePanier();
    }

    @Override
    public void ConsulterListeProduitMagasin(String nommagasin) throws RemoteException {
        for(Magasin m : ListeMagasin)
        {
            if(m.getNom().equals(nommagasin))
            {
               ArrayList<Produit> listeProduits=  m.getListeProduits();
                for(Produit p : listeProduits)
                {
                    System.out.println(p.toString());
                }
            }
        }
    }

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

}
