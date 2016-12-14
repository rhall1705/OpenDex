package personal.rowan.sandbox.ui.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.databinding.ActivityDetail2Binding;
import personal.rowan.sandbox.model.pokemon.Pokemon;
import personal.rowan.sandbox.model.species.PokemonSpecies;
import personal.rowan.sandbox.ui.base.presenter.BasePresenterActivity;
import personal.rowan.sandbox.ui.base.presenter.PresenterFactory;
import personal.rowan.sandbox.ui.detail.dagger.DetailComponent;
import personal.rowan.sandbox.ui.detail.dagger.DetailScope;
import personal.rowan.sandbox.util.PokemonUtil;

/**
 * Created by Rowan Hall
 */

@DetailScope
public class DetailActivity
        extends BasePresenterActivity<DetailPresenter, DetailView>
        implements DetailView, AppBarLayout.OnOffsetChangedListener {

    public static final String ARGS_POKEMON_NUMBER = "ARGS_POKEMON_NUMBER";
    public static final String ARGS_POKEMON_NAME = "ARGS_POKEMON_NAME";

    @Inject
    DetailPresenterFactory mPresenterFactory;

    private DetailPresenter mPresenter;
    private ActivityDetail2Binding mBinding;

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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_2);
        String name = getNameArgument();
        //setToolbar(mBinding.activityDetailTb, PokemonUtil.formatName(name), true);

        SwipeRefreshLayout swipeRefreshLayout = mBinding.activityDetailSrl;
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorSwipeRefresh));
        swipeRefreshLayout.setOnRefreshListener(this::onRefresh);
        swipeRefreshLayout.setEnabled(false);

        Picasso.with(this)
                .load(PokemonUtil.buildPokemonArtworkUrl(name))
                .into(mBinding.activityDetailPlaceholderIv);

        mBinding.activityDetailAbl.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPresenterPrepared(@NonNull DetailPresenter presenter) {
        mPresenter = presenter;
        mPresenter.refreshData(getNumberArgument());
        mPresenter.bindPokedexEntriesButton(mBinding.activityDetailFlavorCardView.onPokedexEntriesClicked());
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
    }

    @Override
    public void showErrorMessage(Throwable e) {
        hideProgress();
        showToastMessage(e.getMessage());
        mBinding.activityDetailSrl.setEnabled(true);
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

    @Override
    public void displayPokedexEntry(PokemonSpecies data) {
        mBinding.activityDetailFlavorCardView.onPokedexEntriesSuccess(data);
    }

    @Override
    public void showPokedexEntryProgress() {
        mBinding.activityDetailFlavorCardView.showPokedexEntriesProgress();
    }

    @Override
    public void showPokedexEntryError(Throwable e) {
        mBinding.activityDetailFlavorCardView.onPokedexEntriesFailure();
        showToastMessage(e.getMessage());
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(mBinding.activityDetailTitleTv, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mBinding.activityDetailTitleTv, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mBinding.activityDetailTitleLl, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mBinding.activityDetailTitleLl, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

}
