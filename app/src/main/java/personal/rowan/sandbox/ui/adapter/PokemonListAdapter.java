package personal.rowan.sandbox.ui.adapter;

import android.view.View;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.model.Result;

/**
 * Created by Rowan Hall
 */

public class PokemonListAdapter
        extends BaseRecyclerViewAdapter<Result> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.listitem_pokemon;
    }

    @Override
    protected BaseViewHolder<Result> buildViewHolder(int viewType, View view) {
        return new PokemonListViewHolder(this, view);
    }

}
