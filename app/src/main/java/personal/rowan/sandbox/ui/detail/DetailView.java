package personal.rowan.sandbox.ui.detail;

import personal.rowan.sandbox.model.PokemonSpecies;

/**
 * Created by Rowan Hall
 */

interface DetailView {

    String getNameArgument();

    void displayPokemon(PokemonSpecies data);

    void showErrorMessage(Throwable e);

    void showProgress();

    void hideProgress();

    void abort();

}
