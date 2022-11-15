package fr.iut.serveur;

import fr.iut.serveur.skeleton.MagasinImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Serveur {

    public static void main(String[] argv)  //Rappel: Possibilité de mettre les ports en arguments
    {
        int port=8000;  //Port Magasin
        int port1=8001; //Port Banque
    try {
        LocateRegistry.createRegistry(port);
        Naming.rebind("rmi://localhost:"+port+"/java", new MagasinImpl());
        System.out.println("Serveur magasin lancé");

    }catch(Exception e) {
        System.out.println("Erreur serveur " + e);
    }
    }
}
