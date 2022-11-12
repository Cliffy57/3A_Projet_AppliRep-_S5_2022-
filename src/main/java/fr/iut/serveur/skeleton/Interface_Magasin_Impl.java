package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import static fr.iut.serveur.modeles.Client.listeClient;

public class Interface_Magasin_Impl extends UnicastRemoteObject implements Interface_Magasin {

    Client current_user;    //Utilisateur du site

    public Interface_Magasin_Impl() throws RemoteException {
    }

    private void Init()
    {
        listeClient.add(new Client("Mel@","123"));   //Ajout d'un client à la liste de client afin d'avoir une base pour bosser
    }

    @Override
    public void CoClient(String mel) throws RemoteException {
        Init();
        vérificationclient(mel);
        System.out.println("Client "+mel+" connecté");
    }
    /**
     * Vérifie si un client est présent dans la liste des clients
     * @param mel : Adresse mel du client
     */
    private boolean vérificationclient(String mel)
    {
        boolean trouve =false;
        for (Client cl: listeClient) {
            if(cl.getMel().equals(mel))
            {
                trouve= true;
                current_user = cl;
            }
        }
        return trouve;

    }

}
