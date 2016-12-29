package personal.rowan.sandbox.ui.main;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;

import java.util.ArrayList;
import java.util.List;

import personal.rowan.sandbox.model.Result;
import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.ui.base.presenter.BasePresenter;
import personal.rowan.sandbox.ui.main.realm.MainViewModelRealmManager;
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
    private MainViewModelRealmManager mRealmManager;
    private Subscription mApiSubscription;
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    private List<MainViewModel> mResult;
    private Integer mCount;
    private Throwable mError;

    MainPresenter(PokemonService pokemonService, MainViewModelRealmManager realmManager) {
        super(MainView.class);
        mPokemonService = pokemonService;
        mRealmManager = realmManager;
        refreshData(null);
    }

    void clearAndRefreshData() {
        refreshData(0, true);
    }

    private void refreshData(Integer offset) {
        refreshData(offset, false);
    }

    private void refreshData(Integer offset, boolean clear) {
        if(isApiSubscriptionActive()) {
            return;
        }

        mCompositeSubscription.add(mApiSubscription = mRealmManager.load()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(realmViewModels -> {
                    if(!clear && isCacheValid(realmViewModels)) {
                        mCount = realmViewModels.size() + 1;
                        return Observable.just(realmViewModels);
                    }

                    return mPokemonService.getPokemonList(offset)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .flatMap(pokemonList -> {
                                mCount = pokemonList.getCount();
                                return Observable.just(createViewModel(pokemonList.getResults(), offset));
                            });
                })
                .subscribe(new Subscriber<List<MainViewModel>>() {
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
                    public void onNext(List<MainViewModel> viewModels) {
                        if(mResult == null) {
                            mResult = new ArrayList<>();
                        }
                        if(clear) {
                            mResult.clear();
                            mRealmManager.clear();
                        }
                        mResult.addAll(viewModels);
                        mRealmManager.update(viewModels);
                        publish();
                    }
                })
        );
    }

    private boolean isCacheValid(List<MainViewModel> realmViewModels) {
        return realmViewModels != null && !realmViewModels.isEmpty() &&
                (mResult == null || mResult.size() < realmViewModels.size());
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
        mRealmManager.close();
        mRealmManager = null;
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
        if(offset == null) offset = 0;
        List<MainViewModel> viewModel = new ArrayList<>();
        for(int i = 0; i < results.size(); i++) {
            Result result = results.get(i);
            viewModel.add(new MainViewModel(result.getName(), offset + i + 1));
        }
        return viewModel;
    }

}
