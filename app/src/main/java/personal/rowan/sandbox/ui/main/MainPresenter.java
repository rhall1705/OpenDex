package personal.rowan.sandbox.ui.main;

import java.util.List;

import personal.rowan.sandbox.model.PokemonList;
import personal.rowan.sandbox.model.Result;
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

class MainPresenter
        extends BasePresenter<MainView> {

    private PokemonService mPokemonService;
    private Subscription mSubscription;
    private List<Result> mResults;
    private Throwable mError;

    MainPresenter(PokemonService pokemonService) {
        super(MainView.class);
        mPokemonService = pokemonService;

        refreshData();
    }

    void refreshData() {
        Observable<PokemonList> pokemonList = mPokemonService.getAllPokemon();
        mSubscription = pokemonList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PokemonList>() {
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
                    public void onNext(PokemonList pokemonList) {
                        mResults = pokemonList.getResults();
                        publish();
                    }
                });
    }

    @Override
    protected void publish() {
        if(mView != null) {
            if(mResults != null) {
                mView.displayPokemonList(mResults);
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
        mResults = null;
        mError = null;
    }

}
