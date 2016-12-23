package personal.rowan.sandbox.ui.detail.coordinator;

import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

/**
 * Created by Rowan Hall
 */

public class DetailOffsetChangedListener
        implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private TextView tvCollapsedTitle;
    private TextView tvExpandedTitle;

    private boolean mCollapsedTitleVisible = false;
    private boolean mExpandedTitleVisible = true;

    public void bind(AppBarLayout appBarLayout, TextView collapsedTitleText, TextView expandedTitleContainer) {
        tvCollapsedTitle = collapsedTitleText;
        tvExpandedTitle = expandedTitleContainer;
        appBarLayout.addOnOffsetChangedListener(this);
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
        if(percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
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
        if(percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
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
