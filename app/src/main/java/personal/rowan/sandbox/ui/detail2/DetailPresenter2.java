package personal.rowan.sandbox.ui.detail2;

import personal.rowan.sandbox.model.pokemon.Pokemon;
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

public class DetailPresenter2
        extends BasePresenter<DetailView2> {

    private PokemonService mPokemonService;
    private Subscription mSubscription;
    private Pokemon mResult;
    private Throwable mError;

    DetailPresenter2(PokemonService pokemonService) {
        super(DetailView2.class);
        mPokemonService = pokemonService;
    }

    void refreshData(Integer number) {
        if(number == null || number <= 0) {
            mView.abort();
        }

        Observable<Pokemon> pokemon = mPokemonService.getPokemon(String.valueOf(number));
        mSubscription = pokemon.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Pokemon>() {
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
                    public void onNext(Pokemon species) {
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
