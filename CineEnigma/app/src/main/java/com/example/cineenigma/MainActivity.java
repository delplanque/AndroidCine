package com.example.cineenigma;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.cineenigma.data.Film;
import com.example.cineenigma.data.FilmManager;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        int id;
        private Button btnDatePicker, btnTimePicker;
        private EditText  titreFilm, descriptionFilm;
        private TextView txtDate, txtTime;
        private EditText noteMusique, noteScenario, noteRealisateur;
private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(TextView)findViewById(R.id.in_date);
        txtTime=(TextView)findViewById(R.id.in_time);
        id = 0;

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
    }


@Override
public void onClick(View v) {

        if (v == btnDatePicker) {

// Get Current Date
final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
        new DatePickerDialog.OnDateSetListener() {

@Override
public void onDateSet(DatePicker view, int year,
        int monthOfYear, int dayOfMonth) {

        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

        }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
        }
        if (v == btnTimePicker) {

// Get Current Time
final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
        new TimePickerDialog.OnTimeSetListener() {

@Override
public void onTimeSet(TimePicker view, int hourOfDay,
        int minute) {

        txtTime.setText(hourOfDay + ":" + minute);
        }
        }, mHour, mMinute, false);
        timePickerDialog.show();
        }
        }

    public void addFilm(View view) {

        try{
        titreFilm=(EditText)findViewById(R.id.titre);
        txtDate=(TextView)findViewById(R.id.in_date);
        txtTime=(TextView)findViewById(R.id.in_time);
        noteScenario=(EditText)findViewById(R.id.noteScenario);
        noteMusique=(EditText)findViewById(R.id.noteMusique);
        noteRealisateur=(EditText)findViewById(R.id.noteRealisateur);
        descriptionFilm=(EditText)findViewById(R.id.description);


        FilmManager m = new FilmManager(this);
        m.open();

        m.addFilm(new Film(id,titreFilm.getText().toString(),txtDate.getText().toString(),txtTime.getText().toString(),
                        Integer.parseInt(noteScenario.getText().toString()),
                        Integer.parseInt(noteMusique.getText().toString()),
                        Integer.parseInt(noteRealisateur.getText().toString()),
                        descriptionFilm.getText().toString()));

        // Listing des enregistrements de la table
        Cursor c = m.getFilms();
        if (c.moveToFirst())
        {
            do {
                Log.d("test",
                        c.getInt(c.getColumnIndex(FilmManager.KEY_ID_FILM)) + "," +
                                c.getString(c.getColumnIndex(FilmManager.KEY_TITRE_FILM))
                );
            }
            while (c.moveToNext());
        }
        c.close(); // fermeture du curseur

        m.close();

        id++;
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        }catch (Exception e){
            TextView error = (TextView)findViewById(R.id.errorText);
            error.setText("Veuillez remplir tout les champs");

        }
    }

    public void AllFilms(View view) {
        Context context = MainActivity.this;

        Class destinationActivity = AllFilms.class;
        Intent startChildActivityIntent = new Intent(context, destinationActivity);
        startActivity(startChildActivityIntent);

    }

    public void Send(View view) {
        FilmManager m = new FilmManager(this);
        m.open();
        // Listing des enregistrements de la table
        String bodySend = "";
        Cursor c = m.getFilms();
        if (c.moveToFirst())
        {
            do {
                bodySend = bodySend + c.getString(c.getColumnIndex(FilmManager.KEY_TITRE_FILM)) + " , " +
                        c.getString(c.getColumnIndex(FilmManager.KEY_NOTE_REALISATEUR_FILM))+ " , " +
                        c.getString(c.getColumnIndex(FilmManager.KEY_NOTE_SCENARIO_FILM))+ " , " +
                        c.getString(c.getColumnIndex(FilmManager.KEY_NOTE_MUSIQUE_FILM)) ;

                bodySend = bodySend + "// ";

            }
            while (c.moveToNext());
        }
        c.close(); // fermeture du curseur

        m.close();

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Critiques");
        i.putExtra(Intent.EXTRA_TEXT   , bodySend);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
