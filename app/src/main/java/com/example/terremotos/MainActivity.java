package com.example.terremotos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    List<Terremoto> listaTerremoto = new ArrayList<>();
    ListView lvTerremoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTerremoto = findViewById(R.id.lvTerremoto);


        //listaTerremoto.add(new Terremoto("8.6","San Francisco","Feb 2 2020"));
        //listaTerremoto.add(new Terremoto("7.5","Los Angeles","Ene 13 2020"));

        request = Volley.newRequestQueue(getApplicationContext());

        cargarWebService();


    }

    private void cargarWebService() {

        String url="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2020-06-01&minmagnitude=5";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {

        listaTerremoto = QueryUtils.extractTerremotos(response);
        // Log.i("tamaño",Integer.toString(listaTerremoto.size()));

        TerremotoAdapter adaptadorDeTerremoto = new TerremotoAdapter(getApplicationContext(),listaTerremoto);
        lvTerremoto.setAdapter(adaptadorDeTerremoto);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se pudo conseguir datos "+error.toString(),
                                            Toast.LENGTH_SHORT).show();
    }

}
