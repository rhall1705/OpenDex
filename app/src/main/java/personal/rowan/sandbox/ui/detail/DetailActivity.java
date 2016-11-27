package personal.rowan.sandbox.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.model.PokemonSpecies;
import personal.rowan.sandbox.ui.base.presenter.BasePresenterActivity;
import personal.rowan.sandbox.ui.base.presenter.PresenterFactory;

/**
 * Created by Rowan Hall
 */

public class DetailActivity
        extends BasePresenterActivity<DetailPresenter, DetailView>
        implements DetailView {

    public static final String ARGS_POKEMON_NAME = "ARGS_POKEMON_NAME";

    private TextView tvName;
    private TextView tvFlavor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setViews();
    }

    private void setViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_detail_tb);
        setToolbar(toolbar, "Pokemon Page", true);

        tvName = (TextView) findViewById(R.id.activity_detail_name_tv);
        tvFlavor = (TextView) findViewById(R.id.activity_detail_flavor_tv);
    }

    @NonNull
    @Override
    protected PresenterFactory<DetailPresenter> getPresenterFactory() {
        return new DetailPresenterFactory(getIntent().getStringExtra(ARGS_POKEMON_NAME));
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

}
