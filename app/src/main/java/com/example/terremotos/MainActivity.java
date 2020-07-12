package com.example.terremotos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Terremoto>>{

    ListView lvTerremoto;
    LoaderManager loaderManager;
    private TextView view_vacio;
    private TerremotoAdapter adaptadorDeTerremoto;
//    private static final String URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
    private static final String USGS_URI = "https://earthquake.usgs.gov/fdsnws/event/1/query";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTerremoto = findViewById(R.id.lvTerremoto);

        view_vacio = findViewById(R.id.view_vacio);
        lvTerremoto.setEmptyView(view_vacio);

        adaptadorDeTerremoto = new TerremotoAdapter(this,new ArrayList<Terremoto>());
        lvTerremoto.setAdapter(adaptadorDeTerremoto);

        loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);

        lvTerremoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String url= adaptadorDeTerremoto.getItem(position).getUrl();
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        loaderManager.restartLoader(1,null,this);
    }

    @Override
    public Loader<List<Terremoto>> onCreateLoader(int id, Bundle args) {

        View indicadorPB = findViewById(R.id.indicadorPB);
        indicadorPB.setVisibility(View.VISIBLE);

        SharedPreferences preferences = getSharedPreferences
                ("Preferencias", Context.MODE_PRIVATE);

        String ordenadoPor  = preferences.getString("orden","magnitude");
        int minimo          = preferences.getInt("minimo",6);
        int resultados      = preferences.getInt("resultados",10);

        Uri baseUri = Uri.parse(USGS_URI);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format","geojson");
        uriBuilder.appendQueryParameter("limit",String.valueOf(resultados));
        uriBuilder.appendQueryParameter("minmag", String.valueOf(minimo));
        uriBuilder.appendQueryParameter("orderby",ordenadoPor);

        return new TerremotoLoader(this,String.valueOf(uriBuilder));
    }

    @Override
    public void onLoadFinished(Loader<List<Terremoto>> loader, List<Terremoto> terremotos) {

        View indicadorPB = findViewById(R.id.indicadorPB);
        indicadorPB.setVisibility(View.GONE);

        view_vacio.setText(R.string.no_encontrado);

        adaptadorDeTerremoto.clear();
        if (terremotos != null && !terremotos.isEmpty()){
            adaptadorDeTerremoto.addAll(terremotos);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Terremoto>> loader) {
        adaptadorDeTerremoto.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.acction_settings) {
            Intent settingsIntent = new Intent(this,preferenciasActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
