package personal.rowan.sandbox.ui.detail2.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.databinding.ViewDetailStatsCardBinding;
import personal.rowan.sandbox.model.pokemon.Pokemon;

/**
 * Created by Rowan Hall
 */

public class DetailStatsCardView extends CardView {

     ViewDetailStatsCardBinding mBinding;

    public DetailStatsCardView(Context context) {
        super(context);
        init(context);
    }

    public DetailStatsCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.view_detail_stats_card, this, true);
    }

    public void setPokemon(Pokemon pokemon) {
        mBinding.setPokemon(pokemon);
    }

}
