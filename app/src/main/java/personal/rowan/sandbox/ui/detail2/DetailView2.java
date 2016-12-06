package personal.rowan.sandbox.ui.detail2;

import personal.rowan.sandbox.model.pokemon.Pokemon;

/**
 * Created by Rowan Hall
 */

interface DetailView2 {

    Integer getNumberArgument();

    String getNameArgument();

    void displayPokemon(Pokemon data);

    void showErrorMessage(Throwable e);

    void showProgress();

    void hideProgress();

    void abort();

}
