package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.Produit;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceMagasin extends Remote {


    public void CoClient(String mel, String nommagasin) throws RemoteException;
    public void AjoutProduit(Produit p )throws RemoteException; //Ajoute un produit au panier du client
    public void ConsulterPanier() throws RemoteException;   //Consulte le panier d'un client
    public void ConsulterListeProduitMagasin(String nommagasin) throws RemoteException;//Consulter la liste des produits existant du magasin

    //Faire un paiement donc établir la co avec l'autre interface

}
