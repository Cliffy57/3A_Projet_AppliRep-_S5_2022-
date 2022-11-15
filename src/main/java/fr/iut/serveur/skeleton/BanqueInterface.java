package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Produit;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BanqueInterface extends Remote {
    public void confirmClientId(Client client);
    public void verifyClient(Client client)throws RemoteException;

}
