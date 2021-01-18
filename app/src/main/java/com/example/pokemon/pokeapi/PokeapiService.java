package com.example.pokemon.pokeapi;

import com.example.pokemon.models.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeapiService {

    @GET("pokemon?limit=151")
    Call<PokemonResponse> getPokemonList();


}
