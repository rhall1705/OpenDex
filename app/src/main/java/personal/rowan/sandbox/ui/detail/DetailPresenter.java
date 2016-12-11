package personal.rowan.sandbox.ui.detail;

import personal.rowan.sandbox.model.pokemon.Pokemon;
import personal.rowan.sandbox.model.species.PokemonSpecies;
import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.ui.base.presenter.BasePresenter;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rowan Hall
 */

class DetailPresenter
        extends BasePresenter<DetailView> {

    private PokemonService mPokemonService;
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    private Pokemon mResult;
    private Throwable mError;

    private Subscription mPokedexEntriesSubscription;
    private PokemonSpecies mPokedexEntriesResult;
    private Throwable mPokedexEntriesError;

    DetailPresenter(PokemonService pokemonService) {
        super(DetailView.class);
        mPokemonService = pokemonService;
    }

    void refreshData(Integer number) {
        if(number == null || number <= 0) {
            mView.abort();
        }

        Observable<Pokemon> pokemon = mPokemonService.getPokemon(String.valueOf(number));
        mCompositeSubscription.add(pokemon
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Pokemon>() {
                    @Override
                    public void onCompleted() {
                        if(mView != null) {
                            mView.hideProgress();
                        }
                        publish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mError = e;
                    }

                    @Override
                    public void onNext(Pokemon result) {
                        mResult = result;
                    }
                }));
    }

    void bindPokedexEntriesButton(Observable<Void> onPokedexEntriesClicked) {
        mCompositeSubscription.add(onPokedexEntriesClicked
                .subscribe(aVoid -> {
                    if(isPokedexEntriesSubscriptionActive()) {
                        return;
                    }

                    Observable<PokemonSpecies> species = mPokemonService.getPokemonSpecies(mResult.getName());
                    mCompositeSubscription.add(mPokedexEntriesSubscription = species
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(() -> {
                                if(mView != null) {
                                    mView.showPokedexEntryProgress();
                                }
                            })
                            .subscribe(new Subscriber<PokemonSpecies>() {
                                @Override
                                public void onCompleted() {
                                    publish();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    mPokedexEntriesError = e;
                                }

                                @Override
                                public void onNext(PokemonSpecies result) {
                                    mPokedexEntriesResult = result;
                                }
                            })
                    );
                })
        );
    }

    private boolean isPokedexEntriesSubscriptionActive() {
        return mPokedexEntriesSubscription != null && !mPokedexEntriesSubscription.isUnsubscribed();
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

            if(mPokedexEntriesResult != null) {
                mView.displayPokedexEntry(mPokedexEntriesResult);
            } else if(mPokedexEntriesError != null) {
                mView.showPokedexEntryError(mPokedexEntriesError);
            } else if(isPokedexEntriesSubscriptionActive()){
                mView.showPokedexEntryProgress();
            }
        }
    }

    @Override
    protected void onDestroyed() {
        mPokemonService = null;
        if(mCompositeSubscription != null) {
            if(!mCompositeSubscription.isUnsubscribed()) {
                mCompositeSubscription.unsubscribe();
            }
            mCompositeSubscription = null;
        }
        mPokedexEntriesSubscription = null;
        mResult = null;
        mError = null;
    }

}
