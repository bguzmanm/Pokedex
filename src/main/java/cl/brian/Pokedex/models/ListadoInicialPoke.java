package cl.brian.Pokedex.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListadoInicialPoke {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String url;

    public ListadoInicialPoke() {
        super();
    }
    public ListadoInicialPoke(String name, String url) {
        super();
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return "ListadoInicialPoke{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
