package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Magasin;
import fr.iut.serveur.modeles.Produit;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MagasinInterface extends Remote {


    public boolean coClient(Client c) throws RemoteException;
    public void ajoutProduit(Produit p )throws RemoteException; //Ajoute un produit au panier du client
    public void consulterPanier() throws RemoteException;   //Consulte le panier d'un client
    public ArrayList<Produit> consulterListeProduitMagasin(String nommagasin) throws RemoteException;//Consulter la liste des produits existant du magasin
    public void ajoutClient(String mail, String motdepasse) throws RemoteException; //Ajoute un nouveau client au magasin
    public double calcSommeProduit(Client cl) throws RemoteException;   //Calcule la somme du panier d'un client
    public Client recupereClientActuel() throws RemoteException;
    //TODO Faire un paiement donc établir la co avec l'autre interface
    double getPrice(String item) throws RemoteException;
    void order(String item) throws RemoteException;
    String getnom() throws  RemoteException;
    public void addItem(String item, double price) throws RemoteException;

    public void addItem2(Produit p)throws RemoteException;

    public void placeOrder(String uuid, double totalCost) throws RemoteException;
}
