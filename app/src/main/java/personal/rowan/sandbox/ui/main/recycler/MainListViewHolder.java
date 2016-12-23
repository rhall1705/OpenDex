package personal.rowan.sandbox.ui.main.recycler;

import android.databinding.DataBindingUtil;
import android.view.View;

import personal.rowan.sandbox.databinding.ListitemPokemonBinding;
import personal.rowan.sandbox.ui.base.BaseRecyclerViewAdapter;
import personal.rowan.sandbox.ui.base.BaseViewHolder;
import personal.rowan.sandbox.ui.main.MainViewModel;

/**
 * Created by Rowan Hall
 */

class MainListViewHolder
        extends BaseViewHolder<MainViewModel> {

    private ListitemPokemonBinding mBinding;

    MainListViewHolder(BaseRecyclerViewAdapter<MainViewModel> adapter, View itemView) {
        super(adapter, itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void onBindView(MainViewModel item) {
        mBinding.setViewModel(item);
    }
}
