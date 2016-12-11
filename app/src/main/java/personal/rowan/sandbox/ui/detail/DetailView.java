package personal.rowan.sandbox.ui.detail;

import personal.rowan.sandbox.model.pokemon.Pokemon;
import personal.rowan.sandbox.model.species.PokemonSpecies;

/**
 * Created by Rowan Hall
 */

interface DetailView {

    Integer getNumberArgument();

    String getNameArgument();

    void displayPokemon(Pokemon data);

    void showErrorMessage(Throwable e);

    void showProgress();

    void hideProgress();

    void abort();

    void onRefresh();

    void displayPokedexEntry(PokemonSpecies data);

    void showPokedexEntryProgress();

    void showPokedexEntryError(Throwable e);

}
