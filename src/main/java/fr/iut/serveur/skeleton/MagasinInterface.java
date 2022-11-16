package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.Produit;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MagasinInterface extends Remote {


    public boolean CoClient(String mel, String nommagasin) throws RemoteException;
    public void AjoutProduit(Produit p )throws RemoteException; //Ajoute un produit au panier du client
    public void ConsulterPanier() throws RemoteException;   //Consulte le panier d'un client
    public ArrayList<Produit> ConsulterListeProduitMagasin(String nommagasin) throws RemoteException;//Consulter la liste des produits existant du magasin

    //TODO Faire un paiement donc établir la co avec l'autre interface
    //TODO Faire une fonction qui calc la somme des prix des produits
}
