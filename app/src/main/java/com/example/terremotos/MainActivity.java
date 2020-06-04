package com.example.terremotos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvTerremoto = findViewById(R.id.lvTerremoto);

        List<Terremoto> listaTerremoto = new ArrayList<>();

        listaTerremoto.add(new Terremoto("8.6","San Francisco","Feb 2 2020"));
        listaTerremoto.add(new Terremoto("7.5","Los Angeles","Ene 13 2020"));

        TerremotoAdapter adaptadorDeTerremoto = new TerremotoAdapter(getApplicationContext(),listaTerremoto);

        lvTerremoto.setAdapter(adaptadorDeTerremoto);


    }
}
