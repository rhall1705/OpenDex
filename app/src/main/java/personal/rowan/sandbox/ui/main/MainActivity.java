package personal.rowan.sandbox.ui.main;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.model.Result;
import personal.rowan.sandbox.ui.BaseActivity;
import personal.rowan.sandbox.ui.adapter.PokemonListAdapter;

public class MainActivity
        extends BaseActivity
        implements MainView, LoaderManager.LoaderCallbacks<MainPresenter> {

    private static final int LOADER_ID = 1;

    private MainPresenter mPresenter;
    private PokemonListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attach(this);
    }

    @Override
    protected void onStop() {
        mPresenter.detach();
        super.onStop();
    }

    private void setViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_tb);
        setToolbar(toolbar, "Pokemon List");

        RecyclerView pokemonList = (RecyclerView) findViewById(R.id.activity_main_rv);
        pokemonList.setLayoutManager(new LinearLayoutManager(this));
        pokemonList.setAdapter(mAdapter = new PokemonListAdapter());
    }

    @Override
    public void displayData(List<Result> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onError(Throwable e) {
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

    @Override
    public Loader<MainPresenter> onCreateLoader(int id, Bundle args) {
        return new MainPresenterLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<MainPresenter> loader, MainPresenter data) {
        mPresenter = data;
    }

    @Override
    public void onLoaderReset(Loader<MainPresenter> loader) {
        mPresenter = null;
    }
}
