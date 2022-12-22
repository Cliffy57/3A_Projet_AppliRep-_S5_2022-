package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Order;
import fr.iut.serveur.modeles.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.UUID;

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
    String nomBanque;
    public BanqueImpl(String name) throws RemoteException {
        super();
        this.nomBanque =name;
        Hashtable<Integer, Client> integerClientHashtable = new Hashtable<>();
        integerClientHashtable.put(1,new Client("hugo@google.com","swage"));
    }

    Client client = new Client("hugo3@google.com","swage");


    //TODO Hashtable<String, Compte> comptes; pour vérif que le compte appartient à la banque
    public void confirmClientId(Client client){
            verifyClient(client);
            System.out.println("Le client n'est pas solvable");

    }
    public void verifyClient(Client client){
     //   if(client.getCodebancaire()==codeBancaire&&client.getSolde()<=montantAchat){
            //TODO Method -> il envoie un message de confirmation au magasin et au client.
        //}
    }


    @Override
    public double getTransactionAmount(Order order) throws RemoteException {
        return 0;
        //TODO order.getAmount();
    }

    @Override
    public void transaction(UUID userId) throws RemoteException {
        if(!userId.toString().isBlank()){
            throw new RemoteException("userId not found: " + userId);
        }
        System.out.println("Transaction made for userID: " + userId);
    }

    @Override
    public void makeTransactionHappen(UUID userId) throws RemoteException {
        //Store the UUID of the user in the Transaction Object
        Transaction transaction = new Transaction(userId);
    }
}
