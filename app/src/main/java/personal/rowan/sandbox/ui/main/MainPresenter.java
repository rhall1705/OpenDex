package personal.rowan.sandbox.ui.main;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;

import personal.rowan.sandbox.model.PokemonList;
import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.ui.base.presenter.BasePresenter;
import personal.rowan.sandbox.util.PokemonUtil;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rowan Hall
 */

class MainPresenter
        extends BasePresenter<MainView> {

    private PokemonService mPokemonService;
    private Subscription mApiSubscription;
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private PokemonList mResult;
    private Throwable mError;

    MainPresenter(PokemonService pokemonService) {
        super(MainView.class);
        mPokemonService = pokemonService;

        refreshData(null);
    }

    void refreshData(Integer offset) {
        if(isApiSubscriptionActive()) {
            return;
        }

        Observable<PokemonList> pokemonList = mPokemonService.getPokemonList(offset);
        mCompositeSubscription.add(mApiSubscription = pokemonList
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
                        if(mResult == null || offset == null) {
                            PokemonUtil.addNumbersToResults(pokemonList.getResults(), 0);
                            mResult = pokemonList;
                        } else {
                            PokemonUtil.addNumbersToResults(pokemonList.getResults(), offset);
                            mResult.getResults().addAll(pokemonList.getResults());
                        }
                        publish();
                    }
                }));
    }

    void bindRecyclerView(Observable<RecyclerViewScrollEvent> observable) {
        mCompositeSubscription.add(observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(scrollEvent -> {
                    if(mView.shouldPaginate() &&
                            !isApiSubscriptionActive()
                            && mResult.getResults().size() < mResult.getCount()) {
                        mView.showProgress();
                        refreshData(mResult.getResults().size());
                    }
                })
        );
    }

    private boolean isApiSubscriptionActive() {
        return mApiSubscription != null && !mApiSubscription.isUnsubscribed();
    }

    @Override
    protected void publish() {
        if(mView != null) {
            if(mResult != null) {
                mView.displayPokemonList(mResult.getResults());
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
        if(mCompositeSubscription != null) {
            if(!mCompositeSubscription.isUnsubscribed()) {
                mCompositeSubscription.unsubscribe();
            }
            mCompositeSubscription = null;
        }
        mApiSubscription = null;
        mResult = null;
        mError = null;
    }

}
