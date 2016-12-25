package personal.rowan.sandbox.ui.detail.coordinator;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

/**
 * Created by Rowan Hall
 */

public class DetailAppBarLayout
        extends AppBarLayout
        implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_COLLAPSED_TITLE = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_EXPANDED_TITLE  = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION            = 200;

    private TextView tvCollapsedTitle;
    private TextView tvExpandedTitle;

    private boolean mCollapsedTitleVisible = false;
    private boolean mExpandedTitleVisible = true;

    public DetailAppBarLayout(Context context) {
        super(context);
    }

    public DetailAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(TextView collapsedTitleText, TextView expandedTitleContainer) {
        tvCollapsedTitle = collapsedTitleText;
        tvExpandedTitle = expandedTitleContainer;
        addOnOffsetChangedListener(this);
        startAlphaAnimation(tvCollapsedTitle, 0, View.INVISIBLE);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if(percentage >= PERCENTAGE_TO_SHOW_COLLAPSED_TITLE) {
            if(!mCollapsedTitleVisible) {
                startAlphaAnimation(tvCollapsedTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mCollapsedTitleVisible = true;
            }
        } else {
            if(mCollapsedTitleVisible) {
                startAlphaAnimation(tvCollapsedTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mCollapsedTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if(percentage >= PERCENTAGE_TO_HIDE_EXPANDED_TITLE) {
            if(mExpandedTitleVisible) {
                startAlphaAnimation(tvExpandedTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mExpandedTitleVisible = false;
            }
        } else {
            if(!mExpandedTitleVisible) {
                startAlphaAnimation(tvExpandedTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mExpandedTitleVisible = true;
            }
        }
    }

    private static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = visibility == View.VISIBLE
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

}
