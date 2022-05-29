package cl.brian.Pokedex.models;

import lombok.Getter;
import lombok.Setter;

public class Abilities {

    @Getter @Setter
    private Ability ability;
    @Getter @Setter
    private boolean is_hidden;
    @Getter @Setter
    private int slot;

    public Abilities() {
        super();
    }

    public Abilities(Ability ability, boolean is_hidden, int slot) {
        this.ability = ability;
        this.is_hidden = is_hidden;
        this.slot = slot;
    }
}
