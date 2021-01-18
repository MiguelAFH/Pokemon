package com.example.pokemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pokemon.models.Pokemon;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    Context contexto;
    ArrayList<Pokemon> pokemons;
    private static LayoutInflater inflater = null;

    public Adaptador(Context contexto, ArrayList<Pokemon> pokemons) {
        this.contexto = contexto;
        this.pokemons = pokemons;
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pokemons.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista,null);

        TextView tvPokemonNombre = vista.findViewById(R.id.tvPokemonName);
        TextView tvPokemonId = vista.findViewById(R.id.tvPokemonId);

        String temp = pokemons.get(position).getName();
        String name = temp.substring(0,1).toUpperCase() + temp.substring(1);

        tvPokemonNombre.setText(name);
        tvPokemonId.setText(contexto.getString(R.string.hashtag) + String.valueOf(pokemons.get(position).getId()));

        return vista;

    }
}
