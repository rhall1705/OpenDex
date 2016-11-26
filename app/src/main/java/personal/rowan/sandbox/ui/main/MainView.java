package personal.rowan.sandbox.ui.main;

import java.util.List;

import personal.rowan.sandbox.model.Result;

/**
 * Created by Rowan Hall
 */

interface MainView {

    void displayPokemonList(List<Result> data);

    void showErrorMessage(Throwable e);

    void showProgress();

    void hideProgress();

}
