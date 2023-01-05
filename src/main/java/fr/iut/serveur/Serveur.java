package fr.iut.serveur;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Ports;
import fr.iut.serveur.skeleton.BanqueImpl;
import fr.iut.serveur.skeleton.BanqueInterface;
import fr.iut.serveur.skeleton.MagasinImpl;
import fr.iut.serveur.skeleton.MagasinInterface;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur {

    public static void main(String[] argv)  //Rappel: Possibilité de mettre les ports en arguments
    {
    try {
        //MagasinImpl shop = new MagasinImpl("shop");
        BanqueImpl bank = new BanqueImpl("bank");
        Registry registry = LocateRegistry.createRegistry(1099);
       // registry.rebind("shop",shop);
        registry.rebind("bank",bank);
        System.out.println("Shop server ready.");
        System.out.println("Bank server ready.");

        LocateRegistry.createRegistry(Ports.Port_Magasin);
        Naming.rebind("rmi://localhost:"+ Ports.Port_Magasin+"/java", new MagasinImpl("shop"));
        Naming.rebind("rmi://localhost:"+ Ports.Port_Banque+"/java", new BanqueImpl("bank"));

        BanqueInterface banq = (BanqueInterface) Naming.lookup("rmi://localhost:"+ Ports.Port_Banque+"/java");
        MagasinInterface magasin = (MagasinInterface) Naming.lookup("rmi://localhost:"+ Ports.Port_Magasin+"/java");
        magasin.setCurrentUser(new Client("user@email.com", "password"));
        magasin.coClient(magasin.recupereClientActuel());
        MagasinImpl mag = new MagasinImpl(magasin.recupereClientActuel());
        registry = LocateRegistry.getRegistry();
        registry.rebind("Magasin", magasin);
        registry.rebind("shop",mag);
        registry.rebind("bank",banq);

        //Naming.rebind("rmi://localhost:"+ Port_Banque+"/java", new BanqueImpl(Port_Banque));
        /*System.out.println("Serveur magasin lancé");
        LocateRegistry.createRegistry(Port_Banque);
        Naming.rebind("rmi://localhost:"+ Port_Banque+"/java", new BanqueImpl(Port_Banque));
        System.out.println("Serveur banque lancé");
*/
    }catch(Exception e) {
        System.out.println("Erreur serveur " + e);
    }
    }
}
