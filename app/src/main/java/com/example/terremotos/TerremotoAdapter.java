package com.example.terremotos;

import android.graphics.drawable.GradientDrawable;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TerremotoAdapter extends ArrayAdapter<Terremoto>  {

    static final String SEPARADOR_LUGAR = " of ";

    public TerremotoAdapter (Context contexto, List<Terremoto> listaTerremoto ){
        super(contexto, 0, listaTerremoto);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View ItemLista = convertView;


        if (ItemLista == null) {
            ItemLista = LayoutInflater.from(getContext()).
                    inflate(R.layout.terremoto_item_lista,parent,false);
        }

        Terremoto itemActual = getItem(position);


        /*
                String hora = new SimpleDateFormat("MMM dd yyyy").
                        format(new Date (horaLong));
*/
        ///-----------------------Sección de Parseo----------------------------------------------///


        String lugar                = "";
        String distancia            = "";
        String fechaFormateada      = "";
        String horaFormateada       = "";
        String magnitudFormateada   = "";
        double magnitudActual       = 0.0;


        if (itemActual != null) {

            //TIEMPO Y HORA
            Date tiempoObj = new Date(itemActual.getHora());
            SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.getDefault());
            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());


            fechaFormateada  = dateFormat.format(tiempoObj);
            horaFormateada   = timeFormat.format(tiempoObj);

            //MAGNITUD
            magnitudActual = itemActual.getMagnitud();
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            magnitudFormateada = decimalFormat.format(magnitudActual);

            //LUGAR
            String lugarCompleto = itemActual.getLugar();
            if ( lugarCompleto.contains(SEPARADOR_LUGAR)){
                String[] partes = lugarCompleto.split(SEPARADOR_LUGAR);
                distancia   = partes[0];
                lugar       = partes[1];
            }else {
                lugar       = lugarCompleto;
                distancia   = getContext().getString(R.string.cerca_de);
            }

        }

        TextView tvMagnitud = ItemLista.findViewById(R.id.tvMagnitud);
        TextView tvLugar    = ItemLista.findViewById(R.id.tvLugar);
        TextView tvDistancia= ItemLista.findViewById(R.id.tvDistancia);
        TextView tvFecha    = ItemLista.findViewById(R.id.tvFecha);
        TextView tvHora     = ItemLista.findViewById(R.id.tvHora);

        tvMagnitud.setText(magnitudFormateada);
        tvLugar.setText(lugar);
        tvDistancia.setText(distancia);
        tvFecha.setText(fechaFormateada);
        tvHora.setText(horaFormateada);

        // CIRCULO
        GradientDrawable circuloMagnitud = (GradientDrawable) tvMagnitud.getBackground();
        int colorMagnitud = getColorMagnitud (magnitudActual);
        circuloMagnitud.setColor(colorMagnitud);

        return ItemLista;

    }

    private int getColorMagnitud(double magnitud) {
        int colorIDMagnitud = 0;
        int magnitudFloor = (int) Math.floor(magnitud);

        switch (magnitudFloor){
            case 0:
                break;
            case 1:
                colorIDMagnitud = R.color.magnitud1;
                break;
            case 2:
                colorIDMagnitud = R.color.magnitud2;
                break;
            case 3:
                colorIDMagnitud = R.color.magnitud3;
                break;
            case 4:
                colorIDMagnitud = R.color.magnitud4;
                break;
            case 5:
                colorIDMagnitud = R.color.magnitud5;
                break;
            case 6:
                colorIDMagnitud = R.color.magnitud6;
                break;
            case 7:
                colorIDMagnitud = R.color.magnitud7;
                break;
            case 8:
                colorIDMagnitud = R.color.magnitud8;
                break;
            case 9:
                colorIDMagnitud = R.color.magnitud9;
                break;
            default:
                colorIDMagnitud = R.color.magnitud10plus;
                break;

        }
        return ContextCompat.getColor(getContext(),colorIDMagnitud);
    }

}
