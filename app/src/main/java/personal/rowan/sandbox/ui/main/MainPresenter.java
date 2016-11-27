package personal.rowan.sandbox.ui.main;

import java.util.List;

import javax.inject.Inject;

import personal.rowan.sandbox.SandboxApplication;
import personal.rowan.sandbox.model.PokemonList;
import personal.rowan.sandbox.model.Result;
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

public class MainPresenter
        extends BasePresenter<MainView> {

    @SuppressWarnings("WeakerAccess")
    @Inject
    Retrofit mRetrofit;

    private Subscription mSubscription;
    private List<Result> mResults;
    private Throwable mError;

    MainPresenter() {
        super(MainView.class);
        SandboxApplication.getInstance().pokeApiComponent().inject(this);

        refreshData();
    }

    void refreshData() {
        PokemonService pokemonService = mRetrofit.create(PokemonService.class);
        Observable<PokemonList> pokemonList = pokemonService.getAllPokemon();
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
