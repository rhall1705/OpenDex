package personal.rowan.sandbox.ui.detail;

import javax.inject.Inject;

import personal.rowan.sandbox.SandboxApplication;
import personal.rowan.sandbox.model.PokemonSpecies;
import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.ui.base.presenter.BasePresenter;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rowan Hall
 */

public class DetailPresenter
        extends BasePresenter<DetailView> {

    @SuppressWarnings("WeakerAccess")
    @Inject
    Retrofit mRetrofit;

    private Subscription mSubscription;
    private PokemonSpecies mResult;
    private Throwable mError;

    DetailPresenter(String name) {
        super(DetailView.class);
        SandboxApplication.getInstance().pokeApiComponent().inject(this);

        PokemonService pokemonService = mRetrofit.create(PokemonService.class);
        Observable<PokemonSpecies> pokemon = pokemonService.getPokemonSpecies(name);
        mSubscription = pokemon.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PokemonSpecies>() {
                    @Override
                    public void onCompleted() {
                        if(mView != null) {
                            mView.hideProgress();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mError = e;
                        publish();
                    }

                    @Override
                    public void onNext(PokemonSpecies species) {
                        mResult = species;
                        publish();
                    }
                });
    }

    @Override
    protected void publish() {
        if(mView != null) {
            if(mResult != null) {
                mView.displayPokemon(mResult);
            } else if(mError != null) {
                mView.showErrorMessage(mError);
            } else {
                mView.showProgress();
            }
        }
    }

}
