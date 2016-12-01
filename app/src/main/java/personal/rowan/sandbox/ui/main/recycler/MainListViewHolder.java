package personal.rowan.sandbox.ui.main.recycler;

import android.view.View;
import android.widget.TextView;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.model.Result;
import personal.rowan.sandbox.ui.base.BaseRecyclerViewAdapter;
import personal.rowan.sandbox.ui.base.BaseViewHolder;

/**
 * Created by Rowan Hall
 */

class MainListViewHolder
        extends BaseViewHolder<Result> {

    private TextView tvTitle;

    MainListViewHolder(BaseRecyclerViewAdapter<Result> adapter, View itemView) {
        super(adapter, itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.listitem_pokemon_title_tv);
    }

    @Override
    public void onBindView(Result item) {
        tvTitle.setText(item.getName());
    }
}
