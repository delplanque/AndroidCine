package com.example.cineenigma.data;

public class Film {

    private int id_film;
    private String titre_film;
    private String date_film;
    private String heure_film;
    private int note_scenario_film;
    private int note_musique_film;
    private int note_realisateur_film;
    private String description_film;


    // Constructeur
    public Film(int id_film,String titre_film,
                String date_film,String heure_film,int note_scenario_film,
                int note_musique_film,int note_realisateur_film,String description_film) {
        this.id_film = id_film;
        this.titre_film = titre_film;
        this.date_film = date_film;
        this.heure_film = heure_film;
        this.note_scenario_film = note_scenario_film;
        this.note_musique_film = note_musique_film;
        this.note_realisateur_film = note_realisateur_film;
        this.description_film = description_film;
    }

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id) {
        this.id_film = id;
    }

    public String getTitre_film() {
        return titre_film;
    }

    public void setTitre_film(String titre) {
        this.titre_film = titre;
    }
    public String getDate_film() {
        return date_film;
    }

    public void setDate_film(String date) {
        this.date_film = date;
    }

    public String getHeure_film() {
        return heure_film;
    }

    public void setHeure_film(String heure) {
        this.heure_film = heure;
    }
    public int getNote_scenario_film() {
        return note_scenario_film;
    }

    public void setNote_scenario_film(int note) {
        this.note_scenario_film = note;
    }

    public int getNote_musique_film() {
        return note_musique_film;
    }

    public void setNote_musique_film(int note) {
        this.note_musique_film = note;
    }

    public int getNote_realisateur_film() {
        return note_realisateur_film;
    }

    public void setNote_realisateur_film(int note) {
        this.note_realisateur_film = note;
    }

    public String getDescription_film() {
        return description_film;
    }

    public void setDescription_film(String description) {
        this.description_film = description;
    }



}
