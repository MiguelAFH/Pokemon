package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
    ImageView ivPokemon;
    ImageView ivType1;
    ImageView ivType2;

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
            ivType1 = findViewById(R.id.ivType1);
            ivType2 = findViewById(R.id.ivType2);

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
                                setOnePokeType(type, ivType1);
                                break;

                            case 2:
                                String type1 = typesJSON.getJSONObject(0).getJSONObject("type").getString("name");
                                String type2 = typesJSON.getJSONObject(1).getJSONObject("type").getString("name");
                                setTwoPokeTypes(type1, type2, ivType1, ivType2);
                                break;

                            default:
                                break;
                        }

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

    private void setOnePokeType(String type1, ImageView ivType1) {
        switch(type1){
            case "bug":
                ivType1.setImageResource(R.drawable.bug);
                break;
            case "dark":
                ivType1.setImageResource(R.drawable.dark);
                break;
            case "dragon":
                ivType1.setImageResource(R.drawable.dragon);
                break;
            case "electric":
                ivType1.setImageResource(R.drawable.electric);
                break;
            case "fairy":
                ivType1.setImageResource(R.drawable.fairy);
                break;
            case "fighting":
                ivType1.setImageResource(R.drawable.fighting);
                break;
            case "fire":
                ivType1.setImageResource(R.drawable.fire);
                break;
            case "flying":
                ivType1.setImageResource(R.drawable.flying);
                break;
            case "ghost":
                ivType1.setImageResource(R.drawable.ghost);
                break;
            case "grass":
                ivType1.setImageResource(R.drawable.grass);
                break;
            case "ground":
                ivType1.setImageResource(R.drawable.ground);
                break;
            case "ice":
                ivType1.setImageResource(R.drawable.ice);
                break;
            case "normal":
                ivType1.setImageResource(R.drawable.normal);
                break;
            case "poison":
                ivType1.setImageResource(R.drawable.poison);
                break;
            case "psychic":
                ivType1.setImageResource(R.drawable.psychic);
                break;
            case "rock":
                ivType1.setImageResource(R.drawable.rock);
                break;
            case "steel":
                ivType1.setImageResource(R.drawable.steel);
                break;
            case "water":
                ivType1.setImageResource(R.drawable.water);
                break;
            default:
                break;
        }
    }

    private void setTwoPokeTypes(String type1, String type2, ImageView ivType1, ImageView ivType2) {
        setOnePokeType(type1, ivType1);
        setOnePokeType(type2, ivType2);


    }

}