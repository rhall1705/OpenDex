package personal.rowan.sandbox.ui.main;

import java.util.List;

import javax.inject.Inject;

import personal.rowan.sandbox.SandboxApplication;
import personal.rowan.sandbox.model.PokemonList;
import personal.rowan.sandbox.model.Result;
import personal.rowan.sandbox.network.PokemonService;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rowan Hall
 */

public class MainPresenter {

    @SuppressWarnings("WeakerAccess")
    @Inject
    Retrofit mRetrofit;

    private MainView mView;
    private List<Result> mResults;
    private Throwable mError;

    MainPresenter() {
        SandboxApplication.getInstance().networkComponent().inject(this);

        PokemonService pokemonService = mRetrofit.create(PokemonService.class);
        Observable<PokemonList> pokemon = pokemonService.getAllPokemon();
        pokemon.subscribeOn(Schedulers.io())
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

    void attach(MainView view) {
        mView = view;
        publish();
    }

    void detach() {
        mView = null;
    }

    private void publish() {
        if(mView != null) {
            if(mResults != null) {
                mView.hideProgress();
                mView.displayData(mResults);
            } else if(mError != null) {
                mView.onError(mError);
            } else {
                mView.showProgress();
            }
        }
    }

}
