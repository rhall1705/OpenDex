package personal.rowan.sandbox.ui.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.databinding.ActivityDetailBinding;
import personal.rowan.sandbox.model.PokemonSpecies;
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
        implements DetailView {

    public static final String ARGS_POKEMON_NAME = "ARGS_POKEMON_NAME";

    @Inject
    DetailPresenterFactory mPresenterFactory;

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
    }

    @Override
    protected void onPresenterPrepared(@NonNull DetailPresenter presenter) {
        presenter.refreshData(getNameArgument());
    }

    @Override
    public String getNameArgument() {
        return getIntent().getStringExtra(ARGS_POKEMON_NAME);
    }

    @Override
    public void displayPokemon(PokemonSpecies data) {
        mBinding.activityDetailNameTv.setText(data.getName());
        mBinding.activityDetailFlavorTv.setText(data.getHabitat().getName());
    }

    @Override
    public void showErrorMessage(Throwable e) {
        hideProgress();
        showToastMessage(e.getMessage());
    }

    @Override
    public void showProgress() {
        showProgressDialog(getString(R.string.activity_detail_progress_title),
                getString(R.string.activity_detail_progress_detail));
    }

    @Override
    public void hideProgress() {
        dismissProgressDialog();
    }

    @Override
    public void abort() {
        showToastMessage(getString(R.string.activity_detail_abort_message));
        finish();
    }

}
