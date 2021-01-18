package com.example.pokemon.pokeapi;

import android.content.res.Resources;

import com.example.pokemon.models.PokemonCharacteristicsResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface PokeCharacteristicsService {

    @GET("pokemon/1/")
    Call<PokemonCharacteristicsResponse> getPokemonCharacteristics();
}
