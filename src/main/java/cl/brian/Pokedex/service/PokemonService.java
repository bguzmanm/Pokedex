package cl.brian.Pokedex.service;

import cl.brian.Pokedex.models.ListadoInicialPoke;
import cl.brian.Pokedex.models.Pokemon;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {

    private final String url_apirest = "https://pokeapi.co/api/v2/pokemon/";

    /**
     * Obtengo listado de pokemones paginados (por defecto),
     * desde API de PokeApi pokeapi.co
     * Llamada sin parámetros al get de Listado de Pokemones
     *
     * @return retorna lista de pokemones.
     * @throws JsonProcessingException excepción Json
     */
    public List<Object> get() throws JsonProcessingException {
        return get(0, 6);
    }

    /**
     * Obtengo listado de pokemones paginados, desde API de PokeApi pokeapi.co
     *
     * @param desde   indica inicio de paginación
     * @param cuantos cantidad de items a retornar
     * @return retorno de lista de pokemones
     * @throws JsonProcessingException excepción Json
     */
    @Cacheable("pokemones")
    public List<Object> get(int desde, int cuantos) throws JsonProcessingException {
        RestTemplate rt = new RestTemplate();

        try {
            ResponseEntity<String> response = rt.getForEntity(url_apirest + "?offset=" +
                    desde + "&limit=" + cuantos, String.class);
            ObjectMapper mapper = new ObjectMapper();

            //Transformo a una lista de ListaInicialPoke
            String strpokes = mapper.readTree(response.getBody()).path("results").toString();
            //obtengo el resto de los datos de los pokemones de la lista.
            List<ListadoInicialPoke> lista = mapper.readValue(strpokes, new TypeReference<List<ListadoInicialPoke>>() {
            });
            //Mapeo pokemones a List<Pokemon>
            List<Pokemon> pokemones = construyeLista(lista);

            //Construyo lista de retorno, donde se incluyen path pasa avanzar y retroceder en
            //paginación de Listado
            List<Object> retorno = new ArrayList<>();
            String[] movimiento = new String[2];
            movimiento[0] = mapper.readTree(response.getBody()).path("previous").toString();
            movimiento[1] = mapper.readTree(response.getBody()).path("next").toString();
            retorno.add(movimiento);
            retorno.add(pokemones);

            return retorno;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Construyo lista de pokemons que serán devueltos en la API.
     *
     * @param listado con formato de la API de PokeApi pokeapi.co
     * @return Listado de objetos Pokemon
     * @throws JsonProcessingException excepción Json
     */
    private List<Pokemon> construyeLista(List<ListadoInicialPoke> listado) throws JsonProcessingException {
        List<Pokemon> lista = new ArrayList<>();

        for (ListadoInicialPoke lip : listado) {
            lista.add(get(lip.getName()));
        }
        return lista;
    }

    /**
     * Obtengo los datos de un Pokemon
     *
     * @param nombre nombre del Pokemon a recuperar
     * @return Retorno instancia de un Pokemon
     * @throws JsonProcessingException excepción Json
     */
    @Cacheable("pokemon")
    public Pokemon get(String nombre) throws JsonProcessingException {
        RestTemplate rt = new RestTemplate();
        try {
            ResponseEntity<String> response = rt.getForEntity(url_apirest + "/" + nombre, String.class);
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

            return mapper.readValue(response.getBody(), Pokemon.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


}
