package fr.iut.serveur.skeleton;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface_Magasin extends Remote {
    //Ajoute un produit au panier du client
    //Consulter la liste des produits existant
    //Consulter le panier du client
    //Faire un paiement donc établir la co avec l'autre interface
    public void CoClient(String mel) throws RemoteException;
}
