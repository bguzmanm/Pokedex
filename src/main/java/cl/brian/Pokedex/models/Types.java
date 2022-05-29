package cl.brian.Pokedex.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Types {

    @Getter @Setter
    private int slot;
    @Getter @Setter
    private List<Type> type;

    public Types(){
        super();
    }

    public Types(int slot, List<Type> type) {
        this.slot = slot;
        this.type = type;
    }

}
