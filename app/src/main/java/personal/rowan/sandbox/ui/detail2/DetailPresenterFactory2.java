package personal.rowan.sandbox.ui.detail2;

import javax.inject.Inject;

import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.ui.base.presenter.PresenterFactory;
import personal.rowan.sandbox.ui.detail2.dagger.DetailScope2;

/**
 * Created by Rowan Hall
 */

@DetailScope2
public class DetailPresenterFactory2
        implements PresenterFactory<DetailPresenter2> {

    private PokemonService mPokemonService;

    @Inject
    DetailPresenterFactory2(PokemonService pokemonService) {
        mPokemonService = pokemonService;
    }

    @Override
    public DetailPresenter2 create() {
        return new DetailPresenter2(mPokemonService);
    }

}
