package personal.rowan.sandbox.network;

import personal.rowan.sandbox.model.PokemonList;
import personal.rowan.sandbox.model.PokemonSpecies;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Rowan Hall
 */

public interface PokemonService {

    String BASE_URL = "http://pokeapi.co/api/v2/";

    @GET("pokemon/")
    Observable<PokemonList> getPokemonList(@Query("offset") Integer offset);

    @GET("pokemon-species/{parameter}/")
    Observable<PokemonSpecies> getPokemonSpecies(@Path("parameter") String parameter);

}
