package personal.rowan.sandbox.ui.main;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;

import java.util.List;

import personal.rowan.sandbox.model.PokemonList;
import personal.rowan.sandbox.model.Result;
import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.ui.base.presenter.BasePresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rowan Hall
 */

class MainPresenter
        extends BasePresenter<MainView> {

    private PokemonService mPokemonService;
    private CompositeSubscription mSubscription = new CompositeSubscription();
    private List<Result> mResults;
    private Throwable mError;

    MainPresenter(PokemonService pokemonService) {
        super(MainView.class);
        mPokemonService = pokemonService;

        refreshData(null);
    }

    void refreshData(Integer offset) {
        Observable<PokemonList> pokemonList = mPokemonService.getPokemonList(offset);
        mSubscription.add(pokemonList
                .subscribeOn(Schedulers.io())
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
                        // If this is the first query, the response becomes the dataset
                        // Otherwise, the response is appended to the dataset, likely due to pagination
                        if(mResults == null || offset == null) {
                            mResults = pokemonList.getResults();
                        } else {
                            mResults.addAll(pokemonList.getResults());
                        }
                        publish();
                    }
                }));
    }

    void bindRecyclerView(Observable<RecyclerViewScrollEvent> observable) {
        mSubscription.add(observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(scrollEvent -> mView.checkForPagination())
        );
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
