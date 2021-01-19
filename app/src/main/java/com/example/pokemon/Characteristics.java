package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokemon.models.PokemonCharacteristics;
import com.example.pokemon.pokeapi.PokeCharacteristicsService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Characteristics extends AppCompatActivity {

    Retrofit retrofit;
    String url;
    String name;
    Context contexto;
    TextView tvNombre;
    TextView tvBaseExperience;
    TextView tvHeight;
    TextView tvWeight;
    TextView tvTypes;
    ImageView ivPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characteristics);

        Bundle bundle = getIntent().getExtras();
        contexto = this;

        if (bundle != null) {

            url = bundle.getString("url");
            name = bundle.getString("name").substring(0,1).toUpperCase() + bundle.getString("name").substring(1);

            ivPokemon =findViewById(R.id.ivPokemon);
            tvNombre = findViewById(R.id.tvName);
            tvBaseExperience = findViewById(R.id.tvBaseExperience);
            tvHeight = findViewById(R.id.tvHeight);
            tvWeight = findViewById(R.id.tvWeight);
            tvTypes = findViewById(R.id.tvTypes);

            retrofit = new Retrofit.Builder()
                    .baseUrl(getResources().getString(R.string.url_pokemons))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            getCharacteristics(contexto);
        }

    }


    private void getCharacteristics(Context contexto) {
        PokeCharacteristicsService service = retrofit.create(PokeCharacteristicsService.class);
        Call<PokemonCharacteristics> pokemonCharacteristicsResponseCall = service.getPokemonCharacteristics(url);

        pokemonCharacteristicsResponseCall.enqueue(new Callback<PokemonCharacteristics>() {
            @Override
            public void onResponse(Call<PokemonCharacteristics> call, Response<PokemonCharacteristics> response) {
                if (response.isSuccessful()) {
                    PokemonCharacteristics pokeCharResponse = response.body();

                    String base_experience = pokeCharResponse.getBase_experience();
                    String height = pokeCharResponse.getHeight();
                    String weight = pokeCharResponse.getWeight();

                    Object sprites = pokeCharResponse.getSprites();
                    Object[] types = pokeCharResponse.getTypes();
                    Gson gson = new Gson();
                    Gson gson2 = new Gson();

                    try {

                        JSONObject spritesJSON = new JSONObject(gson.toJson(sprites));
                        JSONArray typesJSON = new JSONArray(gson2.toJson(types));

                        String urlImage = spritesJSON.getJSONObject("other").getJSONObject("official-artwork").getString("front_default");


                        Picasso.with(contexto)
                                .load(urlImage)
                                .into(ivPokemon);


                        tvNombre.setText(name);
                        tvBaseExperience.setText(base_experience);
                        tvHeight.setText(height);
                        tvWeight.setText(weight);

                        switch (typesJSON.length()){
                            case 1:
                                String type = typesJSON.getJSONObject(0).getJSONObject("type").getString("name");
                                tvTypes.setText(type);
                                break;

                            case 2:
                                String type1 = typesJSON.getJSONObject(0).getJSONObject("type").getString("name");
                                String type2 = typesJSON.getJSONObject(1).getJSONObject("type").getString("name");
                                tvTypes.setText(type1 + ", " + type2);
                                break;

                            default:
                                break;
                        }

                        //Toast.makeText(contexto, "url: " + urlImage, Toast.LENGTH_SHORT).show();

                        //Log.d("Numero tipos","NTipos: " + String.valueOf(typesJSON.length()));
                        //Log.d("Tipos", "Tipos: " + type);
                        //Log.d("RESPUESTA","URL de la imagen del Pokémon leída: " + spritesJSON.getJSONObject("other").getJSONObject("official-artwork").getString("front_default"));




                    } catch (JSONException e) {

                        e.printStackTrace();
                    }


                    //Código para procesar la respuesta

                } else {
                    Log.e("Characteristics " + getResources().getString(R.string.errorResponseTag), getResources().getString(R.string.errorMsgResponse) + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonCharacteristics> call, Throwable t) {
                Log.e(getResources().getString(R.string.errorFailureTag), "Aqui " + getResources().getString(R.string.errorMsgFailure) + t.getMessage());
            }
        });

    }

}