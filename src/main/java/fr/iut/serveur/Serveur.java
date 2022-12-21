package fr.iut.serveur;

import fr.iut.serveur.modeles.ports;
import fr.iut.serveur.skeleton.BanqueImpl;
import fr.iut.serveur.skeleton.MagasinImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static fr.iut.serveur.modeles.ports.Port_Banque;

public class Serveur {

    public static void main(String[] argv)  //Rappel: Possibilité de mettre les ports en arguments
    {
    try {
        MagasinImpl shop = new MagasinImpl("shop");
        BanqueImpl bank = new BanqueImpl("bank");
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("shop",shop);
        registry.rebind("bank",bank);
        System.out.println("Shop server ready.");
        System.out.println("Bank server ready.");

        LocateRegistry.createRegistry(ports.Port_Magasin);
        Naming.rebind("rmi://localhost:"+ ports.Port_Magasin+"/java", new MagasinImpl("shop"));
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
