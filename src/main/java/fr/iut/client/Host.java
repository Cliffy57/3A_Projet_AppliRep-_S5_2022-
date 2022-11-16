package fr.iut.client;

import fr.iut.serveur.modeles.Produit;
import fr.iut.serveur.skeleton.MagasinInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Host { ///Contentera de lancer la vue_selection une fois que ce projet sera fini car ce sont les controleurs qui feront la co avec l'interface

    public static void main(String[] argv)  //Rappel: Possibilité de mettre les ports en arguments
    {
        int port=8000;  //Port Magasin
        int port1=8001; //Port Banque


        try {
            MagasinInterface obj = (MagasinInterface) Naming.lookup("rmi://localhost:"+port+"/java");
            obj.CoClient("Mel@","FLUNCH");
         // obj.ConsulterListeProduitMagasin("FLUNCH");
            Produit p1 = new Produit("APLI Étiquettes Ø 19mm 5 Feuilles 100 Pièces","Doté d'une experience de plus de 60 ans, APLI a pour objectif principal de satisfaire les utilisateurs pour leurs besoins dans le domaine du bureau, informatique, école et maison, industriel.","1.09");

            obj.AjoutProduit(p1);
            obj.ConsulterPanier();
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }

    }
}
