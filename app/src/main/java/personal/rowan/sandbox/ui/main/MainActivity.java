package personal.rowan.sandbox.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.model.Result;
import personal.rowan.sandbox.ui.BasePresenterActivity;
import personal.rowan.sandbox.ui.PresenterFactory;
import personal.rowan.sandbox.ui.adapter.PokemonListAdapter;

public class MainActivity
        extends BasePresenterActivity<MainPresenter, MainView>
        implements MainView {

    private PokemonListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
    }

    private void setViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_tb);
        setToolbar(toolbar, "Pokemon List");

        RecyclerView pokemonList = (RecyclerView) findViewById(R.id.activity_main_rv);
        pokemonList.setLayoutManager(new LinearLayoutManager(this));
        pokemonList.setAdapter(mAdapter = new PokemonListAdapter());
    }

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory() {
        return new MainPresenterFactory();
    }

    @Override
    public void displayPokemonList(List<Result> data) {
        hideProgress();
        mAdapter.setData(data);
    }

    @Override
    public void showErrorMessage(Throwable e) {
        hideProgress();
        showToastMessage(e.getMessage());
    }

    @Override
    public void showProgress() {
        showProgressDialog("Loading Pokemon", "This will only take a moment.");
    }

    @Override
    public void hideProgress() {
        dismissProgressDialog();
    }

}
