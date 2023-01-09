package fr.iut.serveur.skeleton;

        import fr.iut.serveur.modeles.Client;
        import fr.iut.serveur.modeles.Order;

        import java.rmi.Remote;
        import java.rmi.RemoteException;
        import java.util.UUID;

public interface BanqueInterface extends Remote {
    //public void confirmClientId(Client client);
    public void verifyClient(Client client)throws RemoteException;

    double getTransactionAmount(Order order) throws RemoteException;

    void transaction(UUID userId) throws RemoteException;


    void makeTransactionHappen(UUID userId) throws RemoteException;

    boolean processOrder(Client client, double totalCost) throws RemoteException;
}
