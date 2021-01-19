package com.example.pokemon.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonCharacteristics {

    private String name;
    private String base_experience;
    private String height;
    private String weight;
    private Object sprites;
    private Object[] types;




    public PokemonCharacteristics() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(String base_experience) {
        this.base_experience = base_experience;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Object[] getTypes() {
        return types;
    }

    public void setTypes(Object[] types) {
        this.types = types;
    }

    public Object getSprites() {
        return sprites;
    }

    public void setSprites(Object sprites) {
        this.sprites = sprites;
    }


}
