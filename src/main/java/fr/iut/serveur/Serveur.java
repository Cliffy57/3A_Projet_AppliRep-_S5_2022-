package fr.iut.serveur;

import fr.iut.serveur.modeles.ports;
import fr.iut.serveur.skeleton.MagasinImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Serveur {

    public static void main(String[] argv)  //Rappel: Possibilité de mettre les ports en arguments
    {
    try {
        LocateRegistry.createRegistry(ports.Port_Magasin);
        Naming.rebind("rmi://localhost:"+ ports.Port_Magasin+"/java", new MagasinImpl());
        System.out.println("Serveur magasin lancé");

    }catch(Exception e) {
        System.out.println("Erreur serveur " + e);
    }
    }
}
