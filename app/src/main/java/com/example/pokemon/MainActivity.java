package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.pokemon.models.Pokemon;
import com.example.pokemon.models.PokemonResponse;
import com.example.pokemon.pokeapi.PokeapiService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    ArrayList<Pokemon> pokemonList = new ArrayList<>();
    ListView lv;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv = findViewById(R.id.lv);
        contexto = this;

        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url_pokemons))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getPokemons(contexto);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(contexto, Characteristics.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", pokemonList.get(position).getUrl());
                bundle.putString("name", pokemonList.get(position).getName());
                intent.putExtras(bundle);
                startActivity(intent);
                //finish();

                //Toast.makeText(contexto, "id: " + Long.toString(pokemonList.get(position).getId()), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getPokemons(Context contexto) {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonResponse> pokemonResponseCall = service.getPokemonList();

        pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful()) {

                    PokemonResponse pokemonResponse = response.body();
                    pokemonList = pokemonResponse.getResults();

                    for (int i = 0 ; i < pokemonList.size(); i++) {
                        pokemonList.get(i).setId(i+1);
                    }
                    Adaptador adaptador = new Adaptador(contexto, pokemonList);
                    lv.setAdapter(adaptador);

                } else {
                    Log.e(getResources().getString(R.string.errorResponseTag), getResources().getString(R.string.errorMsgResponse) + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.e(getResources().getString(R.string.errorFailureTag), getResources().getString(R.string.errorMsgFailure) + t.getMessage());
            }
        });

    }
}