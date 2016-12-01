package personal.rowan.sandbox.ui.detail;

import android.text.TextUtils;

import personal.rowan.sandbox.model.PokemonSpecies;
import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.ui.base.presenter.BasePresenter;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rowan Hall
 */

class DetailPresenter
        extends BasePresenter<DetailView> {

    private PokemonService mPokemonService;
    private Subscription mSubscription;
    private PokemonSpecies mResult;
    private Throwable mError;

    DetailPresenter(PokemonService pokemonService) {
        super(DetailView.class);
        mPokemonService = pokemonService;
    }

    void refreshData(String name) {
        if(TextUtils.isEmpty(name)) {
            mView.abort();
        }

        Observable<PokemonSpecies> pokemon = mPokemonService.getPokemonSpecies(name);
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

    @Override
    protected void onDestroyed() {
        mPokemonService = null;
        if(mSubscription != null) {
            if(!mSubscription.isUnsubscribed()) {
                mSubscription.unsubscribe();
            }
            mSubscription = null;
        }
        mResult = null;
        mError = null;
    }

}
