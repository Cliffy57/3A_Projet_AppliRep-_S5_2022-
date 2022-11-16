package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.Banque;
import fr.iut.serveur.modeles.Client;

import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

public class BanqueImpl extends UnicastRemoteObject implements BanqueInterface {
    /**
     * Creates and exports a new UnicastRemoteObject object using the
     * particular supplied port.
     *
     * <p>The object is exported with a server socket
     * created using the {@link RMISocketFactory} class.
     *
     * @param port the port number on which the remote object receives calls
     *             (if <code>port</code> is zero, an anonymous port is chosen)
     * @throws RemoteException if failed to export object
     * @since 1.2
     */

    protected BanqueImpl(int port) throws RemoteException {
        super(port);
        Hashtable<Integer, Client> b = new Hashtable<>();
        b.put(1,new Client("hugo@google.com","swage"));
    }

    Client client = new Client("","");


    //TODO Hashtable<String, Compte> comptes; pour vérif que le compte appartient à la banque
    public void confirmClientId(Client client){
       // client.askId();
       // client.askPwd();
       // if(client.askId && client.askPwd ==true){
            verifyClient(client);
        //}else{
            System.out.println("Le client n'est pas solvable");
        //}

    }
    public void verifyClient(Client client){
     //   if(client.getCodebancaire()==codeBancaire&&client.getSolde()<=montantAchat){
            //TODO Method -> il envoie un message de confirmation au magasin et au client.
        //}
    }
}
