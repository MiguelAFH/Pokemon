package com.example.pokemon.models;

import org.json.JSONArray;
import org.json.JSONObject;

public class PokemonCharacteristics {
    private JSONArray abilities;
    private int base_experience;
    private JSONArray forms;
    private JSONArray game_indices;
    private int height;
    private JSONArray held_items;
    private int id;
    private String is_default;
    private String location_area_encounters;
    private JSONArray moves;
    private String name;
    private int order;
    private JSONObject species;
    private JSONObject sprites;
    private JSONArray stats;
    private JSONArray types;
    private int weight;

    public PokemonCharacteristics() {
    }

    public JSONArray getAbilities() {
        return abilities;
    }

    public void setAbilities(JSONArray abilities) {
        this.abilities = abilities;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public JSONArray getForms() {
        return forms;
    }

    public void setForms(JSONArray forms) {
        this.forms = forms;
    }

    public JSONArray getGame_indices() {
        return game_indices;
    }

    public void setGame_indices(JSONArray game_indices) {
        this.game_indices = game_indices;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public JSONArray getHeld_items() {
        return held_items;
    }

    public void setHeld_items(JSONArray held_items) {
        this.held_items = held_items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public void setLocation_area_encounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
    }

    public JSONArray getMoves() {
        return moves;
    }

    public void setMoves(JSONArray moves) {
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public JSONObject getSpecies() {
        return species;
    }

    public void setSpecies(JSONObject species) {
        this.species = species;
    }

    public JSONObject getSprites() {
        return sprites;
    }

    public void setSprites(JSONObject sprites) {
        this.sprites = sprites;
    }

    public JSONArray getStats() {
        return stats;
    }

    public void setStats(JSONArray stats) {
        this.stats = stats;
    }

    public JSONArray getTypes() {
        return types;
    }

    public void setTypes(JSONArray types) {
        this.types = types;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
