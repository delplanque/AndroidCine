package com.example.cineenigma.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FilmManager {


    private static final String TABLE_NAME = "film";
    public static final String KEY_ID_FILM="id_film";
    public static final String KEY_TITRE_FILM="titre_film";
    public static final String KEY_DATE_FILM="date_film";
    public static final String KEY_HEURE_FILM="heure_film";
    public static final String KEY_NOTE_SCENARIO_FILM="note_scenario_film";
    public static final String KEY_NOTE_MUSIQUE_FILM="note_musique_film";
    public static final String KEY_NOTE_REALISATEUR_FILM="note_realisateur_film";
    public static final String KEY_DESCRIPTION_FILM="description_film";
    public static final String CREATE_TABLE_FILM = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_FILM+" INTEGER primary key, " +
            " "+KEY_TITRE_FILM+" TEXT, " +
            " "+KEY_DATE_FILM+" TEXT, " +
            " "+KEY_HEURE_FILM+" TEXT, " +
            " "+KEY_NOTE_SCENARIO_FILM+" INTEGER, " +
            " "+KEY_NOTE_MUSIQUE_FILM+" INTEGER, " +
            " "+KEY_NOTE_REALISATEUR_FILM+" INTEGER, " +
            " "+KEY_DESCRIPTION_FILM+" TEXT" +
            ");";
    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public FilmManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addFilm(Film film) {
        // Ajout d'un enregistrement dans la table

        Log.d("ErrorDB", film.getTitre_film());

        ContentValues values = new ContentValues();

        values.put(KEY_TITRE_FILM, film.getTitre_film());
        values.put(KEY_DATE_FILM, film.getDate_film());
        values.put(KEY_HEURE_FILM, film.getHeure_film());
        values.put(KEY_NOTE_SCENARIO_FILM, film.getNote_scenario_film());
        values.put(KEY_NOTE_MUSIQUE_FILM, film.getNote_musique_film());
        values.put(KEY_NOTE_REALISATEUR_FILM, film.getNote_realisateur_film());
        values.put(KEY_DESCRIPTION_FILM, film.getDescription_film());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }


    public int supFilm(Film film) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_FILM+" = ?";
        String[] whereArgs = {film.getId_film()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Film getFilm(int id) {
        // Retourne l'animal dont l'id est passé en paramètre

        Film a=new Film(0,"","","",0,0,0,"");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_FILM+"="+id, null);
        if (c.moveToFirst()) {
            a.setId_film(c.getInt(c.getColumnIndex(KEY_ID_FILM)));
            a.setTitre_film(c.getString(c.getColumnIndex(KEY_TITRE_FILM)));
            a.setDate_film(c.getString(c.getColumnIndex(KEY_DATE_FILM)));
            a.setHeure_film(c.getString(c.getColumnIndex(KEY_HEURE_FILM)));
            a.setNote_scenario_film(c.getInt(c.getColumnIndex(KEY_NOTE_SCENARIO_FILM)));
            a.setNote_musique_film(c.getInt(c.getColumnIndex(KEY_NOTE_MUSIQUE_FILM)));
            a.setNote_realisateur_film(c.getInt(c.getColumnIndex(KEY_NOTE_REALISATEUR_FILM)));
            a.setDescription_film(c.getString(c.getColumnIndex(KEY_DESCRIPTION_FILM)));
            c.close();
        }

        return a;
    }

    public Cursor getFilms() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }
}
