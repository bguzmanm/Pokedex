package cl.brian.Pokedex.service;

import cl.brian.Pokedex.models.ListadoInicialPoke;
import cl.brian.Pokedex.models.Pokemon;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {

    private final String url_apirest = "https://pokeapi.co/api/v2/pokemon/";

    public List<Object> get() throws JsonProcessingException {
        return get(0, 6);
    }

    public List<Object> get(int desde, int cuantos) throws JsonProcessingException {
        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.getForEntity(url_apirest + "?offset=" +
                desde + "&limit=" + cuantos, String.class);
        ObjectMapper mapper = new ObjectMapper();

        //Transformo a una lista de ListaInicialPoke
        String strpokes = mapper.readTree(response.getBody()).path("results").toString();
        //obtengo el resto de los datos de los pokemones de la lista.
        List<ListadoInicialPoke> lista = mapper.readValue(strpokes, new TypeReference<List<ListadoInicialPoke>>(){});

        List<Pokemon> pokemones = construyeLista(lista);

        List<Object> retorno = new ArrayList<>();

        String movimiento[] = new String[2];
        movimiento[0] = mapper.readTree(response.getBody()).path("previous").toString();
        movimiento[1] = mapper.readTree(response.getBody()).path("next").toString();
        retorno.add(movimiento);
        retorno.add(pokemones);


        return retorno;
    }

    private List<Pokemon> construyeLista(List<ListadoInicialPoke> listado) throws JsonProcessingException {
        List<Pokemon> lista = new ArrayList<Pokemon>();

        for (ListadoInicialPoke lip : listado) {
            lista.add(get(lip.getName()));
        }
        return lista;
    }


    public Pokemon get(String nombre) throws JsonProcessingException {
        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.getForEntity(url_apirest + "/" + nombre, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

        Pokemon p = mapper.readValue(response.getBody(), Pokemon.class);

        return p;
    }


}
