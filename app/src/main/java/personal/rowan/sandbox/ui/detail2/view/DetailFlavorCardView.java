package personal.rowan.sandbox.ui.detail2.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.databinding.ViewDetailFlavorCardBinding;
import personal.rowan.sandbox.model.pokemon.Pokemon;

/**
 * Created by Rowan Hall
 */

public class DetailFlavorCardView
        extends LinearLayout {

    private ViewDetailFlavorCardBinding mBinding;

    public DetailFlavorCardView(Context context) {
        super(context);
        init(context);
    }

    public DetailFlavorCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.view_detail_flavor_card, this, true);
    }

    public void setPokemon(Pokemon pokemon) {
        mBinding.setPokemon(pokemon);
    }

}
