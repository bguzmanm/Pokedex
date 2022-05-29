package cl.brian.Pokedex.models;

import lombok.Getter;
import lombok.Setter;

public class Type {

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String url;

    public Type(){
        super();
    }

    public Type(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
