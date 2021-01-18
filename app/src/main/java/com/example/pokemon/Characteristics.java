package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pokemon.models.PokemonCharacteristics;
import com.example.pokemon.models.PokemonCharacteristicsResponse;
import com.example.pokemon.models.PokemonResponse;
import com.example.pokemon.pokeapi.PokeCharacteristicsService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Characteristics extends AppCompatActivity {

    Retrofit retrofit;
    String id;
    PokemonCharacteristics pokeChar = new PokemonCharacteristics();
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characteristics);

        Bundle bundle = getIntent().getExtras();
        contexto = this;

        if (bundle != null) {

            id = bundle.getString("id");

            Toast.makeText(this, "id: " + id, Toast.LENGTH_SHORT).show();

            retrofit = new Retrofit.Builder()
                    .baseUrl(getResources().getString(R.string.url_characteristics))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            getCharacteristics(contexto);
        }

    }


    private void getCharacteristics(Context contexto) {
        PokeCharacteristicsService service = retrofit.create(PokeCharacteristicsService.class);
        Call<PokemonCharacteristicsResponse> pokemonCharacteristicsResponseCall = service.getPokemonCharacteristics();

        pokemonCharacteristicsResponseCall.enqueue(new Callback<PokemonCharacteristicsResponse>() {
            @Override
            public void onResponse(Call<PokemonCharacteristicsResponse> call, Response<PokemonCharacteristicsResponse> response) {
                if (response.isSuccessful()) {
                    PokemonCharacteristicsResponse pokeCharResponse = response.body();
                    pokeChar = pokeCharResponse.getCharacteristics();
                    Toast.makeText(contexto, "weight: " + String.valueOf(pokeChar.getWeight()), Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Characteristics " + getResources().getString(R.string.errorResponseTag), getResources().getString(R.string.errorMsgResponse) + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonCharacteristicsResponse> call, Throwable t) {
                Log.e(getResources().getString(R.string.errorFailureTag), getResources().getString(R.string.errorMsgFailure) + t.getMessage());
            }
        });

    }
}