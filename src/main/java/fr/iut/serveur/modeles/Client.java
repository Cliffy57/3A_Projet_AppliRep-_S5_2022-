package fr.iut.serveur.modeles;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Client {

    private int id; //Id d'un client
    private String mel;
    private String mdp;
    private String solde; //Solde du compte bancaire d'un client
    private String codebancaire;    //Numéro de code bancaire du client
    private String id_bancaire; //Id de la banque du client (à voir jusqu'où on va dans le projet)

    public static ArrayList<Client> listeClient = new ArrayList<Client>();
    private ArrayList<Produit> Panier = new ArrayList<Produit>(); //Panier d'un client

    public Client(String mel, String mdp)  //La renforcer en mode michel après
    {
        this.id=listeClient.size()+1;
        this.mel=mel;
        this.mdp=mdp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMel() {
        return mel;
    }

    public void setMel(String mel) {
        this.mel = mel;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }

    public static ArrayList<Client> getListeClient() {
        return listeClient;
    }

    public static void setListeClient(ArrayList<Client> listeClient) {
        Client.listeClient = listeClient;
    }

    /**
     * Ajoute un client à la liste des clients existants ?????
     * @param mdp : Mot de passe de client 
     * @param mel : Adresse mel du client
     */
    public void Ajouteclient(String mel, String mdp)
    {
        listeClient.add(new Client(mel,mdp));
    }



    public void ConsultePanier()    //Retour à modifier par la suite
    {
        for(Produit p : Panier)
        {
            System.out.println(p.toString());
        }
    }

}
