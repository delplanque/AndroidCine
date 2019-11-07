package com.example.cineenigma;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cineenigma.data.FilmManager;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class AllFilms extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_films);

        FilmManager m = new FilmManager(this);
        m.open();

        ArrayList<String> TitreFilms = new ArrayList<>();
        ArrayList<String> DescriptionFilms = new ArrayList<>();


        Cursor c = m.getFilms();
        if (c.moveToFirst())
        {
            do {
                TitreFilms.add(c.getString(c.getColumnIndex(FilmManager.KEY_TITRE_FILM)));
                DescriptionFilms.add(c.getString(c.getColumnIndex(FilmManager.KEY_DESCRIPTION_FILM)));
            }
            while (c.moveToNext());
        }
        c.close(); // fermeture du curseur

        LinearLayout lView = new LinearLayout(this);
        TextView myText= new TextView(this);
        lView.addView(myText);
        for(int i=0 ; i < TitreFilms.size(); i++){
            myText.append("Title " + TitreFilms.get(i));
            myText.append("\n");
            myText.append("Description " + DescriptionFilms.get(i));
            myText.append("\n\n");
        }
        setContentView(lView);


        m.close();

    }

}
