package fr.iut.serveur.skeleton;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Order;
import fr.iut.serveur.modeles.Ports;
import fr.iut.serveur.modeles.Transaction;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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

    public boolean processOrder(Client client, double totalCost) throws RemoteException {
        // Check if the client has enough money to complete the order
        if (client.getMoney() >= totalCost) {
            client.setMoney(client.getMoney() - totalCost);
            // Send the order to the server
            BanqueInterface bank = null;
            System.out.println("Order processed for client " + client.getUuid() + " for a total of " + totalCost + " euros");
            // Send a confirmation message to the shop
            MagasinInterface shop = null;
            try {
                shop = (MagasinInterface) Naming.lookup("rmi://localhost:"+ Ports.Port_Magasin+"/java");
                shop.orderConfirmed(client, totalCost);
    return true;
            } catch (NotBoundException | MalformedURLException e) {
                throw new RuntimeException(e);
            }


        } else {
            throw new RemoteException("Client " + client.getUuid() + " does not have enough money to complete the order");
        }
    }

}
