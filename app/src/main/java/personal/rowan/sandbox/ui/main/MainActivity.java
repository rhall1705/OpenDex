package personal.rowan.sandbox.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;

import java.util.List;

import javax.inject.Inject;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.databinding.ActivityMainBinding;
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
    private ActivityMainBinding mBinding;

    private MainListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private void setViews() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setToolbar(mBinding.activityMainTb, getString(R.string.activity_main_title));

        RecyclerView recyclerView = mBinding.activityMainRv;
        recyclerView.setItemAnimator(null);
        recyclerView.setLayoutManager(mLayoutManager = new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter = new MainListAdapter());
        mAdapter.setOnItemClickListener(this);

        SwipeRefreshLayout swipeRefreshLayout = mBinding.activityMainSrl;
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorSwipeRefresh));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected void beforePresenterPrepared() {
        setViews();
        MainComponent.injector.call(this);
    }

    @Override
    protected void onPresenterPrepared(@NonNull MainPresenter presenter) {
        mPresenter = presenter;
        mPresenter.bindRecyclerView(RxRecyclerView.scrollEvents(mBinding.activityMainRv));
    }

    @Override
    protected void onPresenterDestroyed() {
        mPresenter = null;
    }

    @Override
    public void displayPokemonList(List<Result> data) {
        hideProgress();
        mAdapter.paginateData(data);
    }

    @Override
    public void navigateToPokemonDetail(String item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.ARGS_POKEMON_NAME, item);
        startActivity(intent);
    }

    @Override
    public boolean shouldPaginate() {
        return mLayoutManager.findLastVisibleItemPosition() >= mAdapter.getItemCount() - 1;
    }

    @Override
    public void showErrorMessage(Throwable e) {
        hideProgress();
        showToastMessage(e.getMessage());
    }

    @Override
    public void showProgress() {
        mBinding.activityMainSrl.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mBinding.activityMainSrl.setRefreshing(false);
    }

    @Override
    public boolean onItemClick(BaseRecyclerViewAdapter adapter, BaseViewHolder holder, View adapterView, int position) {
        navigateToPokemonDetail(mAdapter.getItem(position).getName());
        return true;
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshData(null);
    }

}
