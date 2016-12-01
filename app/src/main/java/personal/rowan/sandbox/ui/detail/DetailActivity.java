package personal.rowan.sandbox.ui.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import javax.inject.Inject;

import personal.rowan.sandbox.R;
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

    private TextView tvName;
    private TextView tvFlavor;

    @NonNull
    @Override
    protected PresenterFactory<DetailPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected void beforePresenterPrepared() {
        setContentView(R.layout.activity_detail);
        DetailComponent.injector.call(this);
        setViews();
    }

    private void setViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_detail_tb);
        setToolbar(toolbar, "Pokemon Page", true);

        tvName = (TextView) findViewById(R.id.activity_detail_name_tv);
        tvFlavor = (TextView) findViewById(R.id.activity_detail_flavor_tv);
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
        tvName.setText(data.getName());
        tvFlavor.setText(data.getHabitat().getName());
    }

    @Override
    public void showErrorMessage(Throwable e) {
        hideProgress();
        showToastMessage(e.getMessage());
    }

    @Override
    public void showProgress() {
        showProgressDialog("Loading Pokemon Data", "This will only take a moment.");
    }

    @Override
    public void hideProgress() {
        dismissProgressDialog();
    }

    @Override
    public void abort() {
        showToastMessage("Pokemon data not found.");
        finish();
    }

}
