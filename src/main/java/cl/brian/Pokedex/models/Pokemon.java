package cl.brian.Pokedex.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Pokemon {

    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter @JsonIgnoreProperties
    private List<Types> types;
    @Getter @Setter
    private float weight;
    @Getter @Setter @JsonIgnoreProperties
    private List<Abilities> abilities;

    public Pokemon(){
        super();
    }

    public Pokemon(int id, String name, List<Types> types, float weight, List<Abilities> abilities) {
        this.name = name;
        this.types = types;
        this.weight = weight;
        this.abilities = abilities;
    }
}