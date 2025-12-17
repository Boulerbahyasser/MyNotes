package com.example.myapplication.model;

public class Note {
    private final String nom;
    private final String description;
    private final String date;
    private final String priorite; // Basse, Moyenne, Haute

    public Note(String nom, String description, String date, String priorite) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.priorite = priorite;
    }

    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getPriorite() { return priorite; }
}
