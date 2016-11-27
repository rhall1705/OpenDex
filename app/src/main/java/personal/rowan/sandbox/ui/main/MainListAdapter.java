package personal.rowan.sandbox.ui.main;

import android.view.View;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.model.Result;
import personal.rowan.sandbox.ui.base.BaseRecyclerViewAdapter;
import personal.rowan.sandbox.ui.base.BaseViewHolder;

/**
 * Created by Rowan Hall
 */

class MainListAdapter
        extends BaseRecyclerViewAdapter<Result> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.listitem_pokemon;
    }

    @Override
    protected BaseViewHolder<Result> buildViewHolder(int viewType, View view) {
        return new MainListViewHolder(this, view);
    }

}
