package personal.rowan.sandbox.ui.main;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by Rowan Hall
 */

public class MainPresenterLoader
        extends Loader<MainPresenter> {

    private MainPresenter mPresenter;

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public MainPresenterLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mPresenter == null) {
            forceLoad();
        } else {
            deliverResult(mPresenter);
        }
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        mPresenter = new MainPresenter();

        deliverResult(mPresenter);
    }

    @Override
    protected void onReset() {
        super.onReset();
        //potentially let presenter know its done.
    }

}
