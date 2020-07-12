package com.example.terremotos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

public class preferenciasActivity extends AppCompatActivity {


    RadioGroup  rgOrdenadoPor;
    EditText    etMagnitudMinima,etResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);

        rgOrdenadoPor       = findViewById(R.id.rgOrdenadoPor);
        etMagnitudMinima    = findViewById(R.id.etMagnitudMinima);
        etResultados        = findViewById(R.id.etResultados);

        cargarPreferencias();

    }

    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences
                ("Preferencias", Context.MODE_PRIVATE);

        String ordenadoPor = preferences.getString("orden","magnitude");
        switch(ordenadoPor) {

            case "magnitude":
                rgOrdenadoPor.check(R.id.rbPorMagnitud);
                break;
            case "time":
                rgOrdenadoPor.check(R.id.rbPorMasRecientes);
                break;

        }
        int minimo = preferences.getInt("minimo",6);
        etMagnitudMinima.setText(String.valueOf(minimo));

        int resultados = preferences.getInt("resultados", 10);
        etResultados.setText(String.valueOf(resultados));

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        guardarPreferencias();
    }

    private void guardarPreferencias() {

        int intSeleccionadoDeID     = rgOrdenadoPor.getCheckedRadioButtonId();
        String ordenadoPor;
        switch(intSeleccionadoDeID) {

            case R.id.rbPorMagnitud:
                ordenadoPor = "magnitude";
                break;
            case R.id.rbPorMasRecientes:
                ordenadoPor = "time";
                break;

            default:
                ordenadoPor = "";
                break;
        }

        int minimo      = Integer.parseInt(etMagnitudMinima.getText().toString());
        int resultados  = Integer.parseInt(etResultados.getText().toString());

        SharedPreferences preferences = getSharedPreferences
                ("Preferencias", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("orden",ordenadoPor);
        editor.putInt("minimo",minimo);
        editor.putInt("resultados",resultados);

        editor.apply();

    }
}