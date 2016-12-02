package personal.rowan.sandbox.ui.main.recycler;

import android.databinding.DataBindingUtil;
import android.view.View;

import personal.rowan.sandbox.databinding.ListitemPokemonBinding;
import personal.rowan.sandbox.model.Result;
import personal.rowan.sandbox.ui.base.BaseRecyclerViewAdapter;
import personal.rowan.sandbox.ui.base.BaseViewHolder;

/**
 * Created by Rowan Hall
 */

class MainListViewHolder
        extends BaseViewHolder<Result> {

    private ListitemPokemonBinding mBinding;

    MainListViewHolder(BaseRecyclerViewAdapter<Result> adapter, View itemView) {
        super(adapter, itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void onBindView(Result item) {
        mBinding.setResult(item);
    }
}
