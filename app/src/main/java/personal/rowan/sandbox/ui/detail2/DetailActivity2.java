package personal.rowan.sandbox.ui.detail2;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.databinding.ActivityDetail2Binding;
import personal.rowan.sandbox.model.pokemon.Pokemon;
import personal.rowan.sandbox.ui.base.presenter.BasePresenterActivity;
import personal.rowan.sandbox.ui.base.presenter.PresenterFactory;
import personal.rowan.sandbox.ui.detail2.dagger.DetailComponent2;
import personal.rowan.sandbox.ui.detail2.dagger.DetailScope2;
import personal.rowan.sandbox.util.PokemonUtil;

/**
 * Created by Rowan Hall
 */

@DetailScope2
public class DetailActivity2
        extends BasePresenterActivity<DetailPresenter2, DetailView2>
        implements DetailView2, SwipeRefreshLayout.OnRefreshListener {

    public static final String ARGS_POKEMON_NUMBER = "ARGS_POKEMON_NUMBER";
    public static final String ARGS_POKEMON_NAME = "ARGS_POKEMON_NAME";

    @Inject
    DetailPresenterFactory2 mPresenterFactory;

    private DetailPresenter2 mPresenter;
    private ActivityDetail2Binding mBinding;

    @NonNull
    @Override
    protected PresenterFactory<DetailPresenter2> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected void beforePresenterPrepared() {
        setViews();
        DetailComponent2.injector.call(this);
    }

    private void setViews() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_2);
        String name = getNameArgument();
        setToolbar(mBinding.activityDetailTb, PokemonUtil.formatName(name), true);

        SwipeRefreshLayout swipeRefreshLayout = mBinding.activityDetailSrl;
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorSwipeRefresh));
        swipeRefreshLayout.setOnRefreshListener(this);

        Picasso.with(this)
                .load(PokemonUtil.buildPokmonUrl(name))
                .into(mBinding.activityDetailHeaderIv);
    }

    @Override
    protected void onPresenterPrepared(@NonNull DetailPresenter2 presenter) {
        mPresenter = presenter;
        mPresenter.refreshData(getNumberArgument());
    }

    @Override
    public Integer getNumberArgument() {
        return getIntent().getIntExtra(ARGS_POKEMON_NUMBER, -1);
    }

    @Override
    public String getNameArgument() {
        return getIntent().getStringExtra(ARGS_POKEMON_NAME);
    }

    @Override
    public void displayPokemon(Pokemon data) {
        mBinding.setPokemon(data);
        mBinding.activityDetailSpritesCardView.loadSprites(data);
    }

    @Override
    public void showErrorMessage(Throwable e) {
        hideProgress();
        showToastMessage(e.getMessage());
    }

    @Override
    public void showProgress() {
        mBinding.activityDetailContentLl.setVisibility(View.GONE);
        mBinding.activityDetailSrl.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mBinding.activityDetailContentLl.setVisibility(View.VISIBLE);
        mBinding.activityDetailSrl.setRefreshing(false);
    }

    @Override
    public void abort() {
        showToastMessage(getString(R.string.activity_detail_abort_message));
        finish();
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshData(getNumberArgument());
    }
}
