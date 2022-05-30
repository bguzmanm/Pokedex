package cl.brian.Pokedex;
import static org.assertj.core.api.Assertions.assertThat;
import cl.brian.Pokedex.restService.PokemonRest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest
class PokedexApplicationTests {

	@Autowired
	private PokemonRest controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();

	}
	@Test
	public void retornoExitoso() throws JsonProcessingException {
		assertThat((this.controller.getPokemons("0","6")).size()==6);
	}

	@Test
	public void retornoExitosoUno() throws JsonProcessingException {
		assertThat(this.controller.getPokemon("bulbasaur").getName().equals("bulbasaur"));
	}

}
