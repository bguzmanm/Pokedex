package cl.brian.Pokedex.restService;

import cl.brian.Pokedex.models.Pokemon;
import cl.brian.Pokedex.service.PokemonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PokemonRest {

    @Autowired
    private PokemonService ps = new PokemonService();

    @RequestMapping(value = "pokeRest")
    public List<Object> getPokemons(@RequestParam(name = "offset", required = false) String offset,
                                    @RequestParam(name = "limit", required = false) String limit)
            throws JsonProcessingException {

        if ((offset != null) && (limit != null)){
            if (isNumeric(offset) && isNumeric(limit)){
                return ps.get(Integer.parseInt(offset), Integer.parseInt(limit));
            } else {
                return ps.get();
            }
        } else {
            return ps.get();
        }
    }

    @RequestMapping(value = "PokeRestOne")
    public Pokemon getPokemon(@PathVariable(name = "nombre") String nombre) throws JsonProcessingException {
        return ps.get(nombre);
    }


    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
