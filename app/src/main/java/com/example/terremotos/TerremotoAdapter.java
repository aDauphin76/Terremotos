package com.example.terremotos;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TerremotoAdapter extends ArrayAdapter<Terremoto> {

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

        TextView tvManitud  = ItemLista.findViewById(R.id.tvMagnitud);
        TextView tvLugar    = ItemLista.findViewById(R.id.tvLugar);
        TextView tvHora     = ItemLista.findViewById(R.id.tvHora);

        tvManitud.setText(itemActual.getMagnitud());
        tvLugar.setText(itemActual.getLugar());
        tvHora.setText(itemActual.getHora());

        return ItemLista;
    }
}
