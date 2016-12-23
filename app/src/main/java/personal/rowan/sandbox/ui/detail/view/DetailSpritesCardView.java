package personal.rowan.sandbox.ui.detail.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.databinding.ViewDetailSpritesCardBinding;
import personal.rowan.sandbox.ui.detail.DetailViewModel;

/**
 * Created by Rowan Hall
 */

public class DetailSpritesCardView
        extends LinearLayout {

    private ViewDetailSpritesCardBinding mBinding;

    public DetailSpritesCardView(Context context) {
        super(context);
        init(context);
    }

    public DetailSpritesCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.view_detail_sprites_card, this, true);
    }

    public void setViewModel(DetailViewModel.DetailSpritesCardViewModel viewModel) {
        mBinding.setViewModel(viewModel);
    }

}
