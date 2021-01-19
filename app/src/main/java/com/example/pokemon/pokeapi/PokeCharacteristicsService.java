package com.example.pokemon.pokeapi;

import com.example.pokemon.models.PokemonCharacteristics;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;


public interface PokeCharacteristicsService {

    @GET
    Call<PokemonCharacteristics> getPokemonCharacteristics(
            @Url String url
    );
}
