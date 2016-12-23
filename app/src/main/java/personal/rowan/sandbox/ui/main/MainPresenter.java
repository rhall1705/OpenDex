package personal.rowan.sandbox.ui.main;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;

import java.util.ArrayList;
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
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rowan Hall
 */

class MainPresenter
        extends BasePresenter<MainView> {

    private PokemonService mPokemonService;
    private Subscription mApiSubscription;
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    private List<MainViewModel> mResult;
    private Integer mCount;
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
                        mCount = pokemonList.getCount();
                        // If this is the first query, the response becomes the dataset
                        // Otherwise, the response is appended to the dataset, likely due to pagination
                        if(mResult == null || offset == null) {
                            mResult = createViewModel(pokemonList.getResults(), 0);
                        } else {
                            mResult.addAll(createViewModel(pokemonList.getResults(), offset));
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
                            && mResult.size() < mCount) {
                        mView.showProgress();
                        refreshData(mResult.size());
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
                mView.displayPokemonList(mResult);
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
        mCount = null;
        mError = null;
    }

    private static List<MainViewModel> createViewModel(List<Result> results, Integer offset) {
        List<MainViewModel> viewModel = new ArrayList<>();
        for(int i = 0; i < results.size(); i++) {
            Result result = results.get(i);
            viewModel.add(new MainViewModel(result.getName(), offset + i + 1));
        }
        return viewModel;
    }

}
