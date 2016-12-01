package personal.rowan.sandbox.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.model.Result;
import personal.rowan.sandbox.ui.base.BaseRecyclerViewAdapter;
import personal.rowan.sandbox.ui.base.BaseViewHolder;
import personal.rowan.sandbox.ui.base.presenter.BasePresenterActivity;
import personal.rowan.sandbox.ui.base.presenter.PresenterFactory;
import personal.rowan.sandbox.ui.detail.DetailActivity;
import personal.rowan.sandbox.ui.main.dagger.MainComponent;
import personal.rowan.sandbox.ui.main.dagger.MainScope;
import personal.rowan.sandbox.ui.main.recycler.MainListAdapter;

@MainScope
public class MainActivity
        extends BasePresenterActivity<MainPresenter, MainView>
        implements MainView, BaseRecyclerViewAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    MainPresenterFactory mPresenterFactory;

    private MainPresenter mPresenter;
    private MainListAdapter mAdapter;
    private SwipeRefreshLayout srlList;

    private void setViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_tb);
        setToolbar(toolbar, "Pokemon List");

        RecyclerView pokemonList = (RecyclerView) findViewById(R.id.activity_main_rv);
        pokemonList.setLayoutManager(new LinearLayoutManager(this));
        pokemonList.setAdapter(mAdapter = new MainListAdapter());
        mAdapter.setOnItemClickListener(this);

        srlList = (SwipeRefreshLayout) findViewById(R.id.activity_main_srl);
        srlList.setOnRefreshListener(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected void beforePresenterPrepared() {
        setContentView(R.layout.activity_main);
        MainComponent.injector.call(this);
        setViews();
    }

    @Override
    protected void onPresenterPrepared(@NonNull MainPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onPresenterDestroyed() {
        mPresenter = null;
    }

    @Override
    public void displayPokemonList(List<Result> data) {
        hideProgress();
        mAdapter.setData(data);
    }

    @Override
    public void navigateToPokemonDetail(String item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.ARGS_POKEMON_NAME, item);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(Throwable e) {
        hideProgress();
        showToastMessage(e.getMessage());
    }

    @Override
    public void showProgress() {
        srlList.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        srlList.setRefreshing(false);
    }

    @Override
    public boolean onItemClick(BaseRecyclerViewAdapter adapter, BaseViewHolder holder, View adapterView, int position) {
        navigateToPokemonDetail(mAdapter.getItem(position).getName());
        return true;
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshData();
    }

}
