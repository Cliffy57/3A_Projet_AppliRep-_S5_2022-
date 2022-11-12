package fr.iut.client;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.skeleton.Interface_Magasin;
import fr.iut.serveur.skeleton.Interface_Magasin_Impl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static fr.iut.serveur.modeles.Client.listeClient;

public class Host { ///Contentera de lancer la vue_selection une fois que ce projet sera fini car ce sont les controleurs qui feront la co avec l'interface

    public static void main(String[] argv)  //Rappel: Possibilité de mettre les ports en arguments
    {
        int port=8000;  //Port Magasin
        int port1=8001; //Port Banque


        try {
            Interface_Magasin obj = (Interface_Magasin) Naming.lookup("rmi://localhost:"+port+"/java");
            obj.CoClient("Mel@");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
