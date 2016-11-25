package personal.rowan.sandbox.ui.adapter;

import android.view.View;
import android.widget.TextView;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.model.Result;

/**
 * Created by Rowan Hall
 */

public class PokemonListViewHolder
        extends BaseViewHolder<Result> {

    private TextView tvTitle;

    public PokemonListViewHolder(BaseRecyclerViewAdapter<Result> adapter, View itemView) {
        super(adapter, itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.listitem_pokemon_title_tv);
    }

    @Override
    public void onBindView(Result item) {
        tvTitle.setText(item.getName());
    }
}
