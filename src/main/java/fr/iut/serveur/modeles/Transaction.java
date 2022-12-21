package fr.iut.serveur.modeles;

import java.util.UUID;

public class Transaction{
    public Transaction(UUID userId) {
        System.out.println("Transaction made by user: " + userId);
    }
}
