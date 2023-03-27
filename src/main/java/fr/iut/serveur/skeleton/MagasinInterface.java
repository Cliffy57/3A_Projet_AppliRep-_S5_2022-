package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Magasin;
import fr.iut.serveur.modeles.Produit;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MagasinInterface extends Remote {



    public boolean coClient(Client c) throws RemoteException;   //Connecte un client au magasin
    public void ajoutProduit(Produit p )throws RemoteException; //Ajoute un produit au panier du client
    public void consulterPanier() throws RemoteException;   //Consulte le panier d'un client
    public ArrayList<Produit> consulterListeProduitMagasin(String nommagasin) throws RemoteException;//Consulter la liste des produits existant du magasin
    public void ajoutClient(String mail, String motdepasse) throws RemoteException; //Ajoute un nouveau client au magasin
    public double calcSommeProduit(Client cl) throws RemoteException;   //Calcule la somme du panier d'un client
    public Client recupereClientActuel() throws RemoteException;    //Récupère le client
    double getPrice(String item) throws RemoteException;    //Affiche le prix d'un produit d'une commande
    void order(String item) throws RemoteException;
    String getnom() throws  RemoteException; //Récupère le nom du magasin

 

    public boolean placeOrder(String uuid, double totalCost) throws RemoteException; //Crée une commande

    public void setCurrentUser(Client password) throws RemoteException; //Setter client

    public void orderConfirmed(Client client, double totalCost) throws RemoteException; //Confirme la commande
}
