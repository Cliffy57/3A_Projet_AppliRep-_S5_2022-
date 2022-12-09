package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Produit;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MagasinInterface extends Remote {


    public boolean CoClient(String mel, String nommagasin) throws RemoteException;
    public void AjoutProduit(Produit p )throws RemoteException; //Ajoute un produit au panier du client
    public void ConsulterPanier() throws RemoteException;   //Consulte le panier d'un client
    public ArrayList<Produit> ConsulterListeProduitMagasin(String nommagasin) throws RemoteException;//Consulter la liste des produits existant du magasin
    public void AjoutClient(String mail, String motdepasse) throws RemoteException; //Ajoute un nouveau client au magasin
    public double CalcSommeProduit(Client cl) throws RemoteException;   //Calcule la somme du panier d'un client
    public Client RecupereClientActuel() throws RemoteException;// TODO Soit on laisse ce getter soit lorsqu'on build le ctrl du magasin on recup en plus du nom l'object client mais risque de doublons parce qu'on a un truc client dans le ctrl et dans l'impl
    //TODO Faire un paiement donc établir la co avec l'autre interface

}
