package cl.brian.Pokedex.models;

import lombok.Getter;
import lombok.Setter;

public class Ability {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String url;

    public Ability (){
        super();
    }

    public Ability(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
