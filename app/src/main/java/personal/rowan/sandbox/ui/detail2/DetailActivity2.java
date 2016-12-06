package personal.rowan.sandbox.ui.detail2;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;

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
        implements DetailView2 {

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
        Picasso.with(this)
                .load(PokemonUtil.buildPokmonUrl(name))
                .into(mBinding.activityDetailHeaderIv);
    }

    @Override
    protected void onPresenterPrepared(@NonNull DetailPresenter2 presenter) {
        mPresenter = presenter;
        mPresenter.refreshData(getIndexArgument());
    }

    @Override
    public Integer getIndexArgument() {
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
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void abort() {
        showToastMessage(getString(R.string.activity_detail_abort_message));
        finish();
    }
}
