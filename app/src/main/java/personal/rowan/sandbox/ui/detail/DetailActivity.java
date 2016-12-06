package personal.rowan.sandbox.ui.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;

import javax.inject.Inject;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.databinding.ActivityDetailBinding;
import personal.rowan.sandbox.model.species.PokemonSpecies;
import personal.rowan.sandbox.ui.base.presenter.BasePresenterActivity;
import personal.rowan.sandbox.ui.base.presenter.PresenterFactory;
import personal.rowan.sandbox.ui.detail.dagger.DetailComponent;
import personal.rowan.sandbox.ui.detail.dagger.DetailScope;

/**
 * Created by Rowan Hall
 */

@DetailScope
public class DetailActivity
        extends BasePresenterActivity<DetailPresenter, DetailView>
        implements DetailView, SwipeRefreshLayout.OnRefreshListener {

    public static final String ARGS_POKEMON_NAME = "ARGS_POKEMON_NAME";

    @Inject
    DetailPresenterFactory mPresenterFactory;

    private DetailPresenter mPresenter;
    private ActivityDetailBinding mBinding;

    @NonNull
    @Override
    protected PresenterFactory<DetailPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected void beforePresenterPrepared() {
        setViews();
        DetailComponent.injector.call(this);
    }

    private void setViews() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        setToolbar(mBinding.activityDetailTb, getString(R.string.activity_detail_title), true);

        SwipeRefreshLayout swipeRefreshLayout = mBinding.activityDetailSrl;
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorSwipeRefresh));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onPresenterPrepared(@NonNull DetailPresenter presenter) {
        mPresenter = presenter;
        mPresenter.refreshData(getNameArgument());
    }

    @Override
    public String getNameArgument() {
        return getIntent().getStringExtra(ARGS_POKEMON_NAME);
    }

    @Override
    public void displayPokemon(PokemonSpecies data) {
        mBinding.setPokemonSpecies(data);
    }

    @Override
    public void showErrorMessage(Throwable e) {
        hideProgress();
        showToastMessage(e.getMessage());
    }

    @Override
    public void showProgress() {
        mBinding.activityDetailSrl.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mBinding.activityDetailSrl.setRefreshing(false);
    }

    @Override
    public void abort() {
        showToastMessage(getString(R.string.activity_detail_abort_message));
        finish();
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshData(getNameArgument());
    }

}
