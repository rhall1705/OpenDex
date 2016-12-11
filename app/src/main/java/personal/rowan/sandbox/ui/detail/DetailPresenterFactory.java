package personal.rowan.sandbox.ui.detail;

import javax.inject.Inject;

import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.ui.base.presenter.PresenterFactory;
import personal.rowan.sandbox.ui.detail.dagger.DetailScope;

/**
 * Created by Rowan Hall
 */

@DetailScope
class DetailPresenterFactory
        implements PresenterFactory<DetailPresenter> {

    private PokemonService mPokemonService;

    @Inject
    DetailPresenterFactory(PokemonService pokemonService) {
        mPokemonService = pokemonService;
    }

    @Override
    public DetailPresenter create() {
        return new DetailPresenter(mPokemonService);
    }

}
