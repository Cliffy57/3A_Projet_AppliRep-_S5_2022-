package fr.iut.serveur.modeles;

import fr.iut.serveur.skeleton.BanqueImpl;

import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.util.Hashtable;

public class Banque extends BanqueImpl {
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
    public Banque(int port) throws RemoteException {
        super(port);
    }
}
