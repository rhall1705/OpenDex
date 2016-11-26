package personal.rowan.sandbox.ui;

import personal.rowan.sandbox.util.NullObject;

/**
 * Created by Rowan Hall
 */

public abstract class BasePresenter<V> {

    protected V mView;
    private Class<V> mViewClazz;

    public BasePresenter(Class<V> viewClazz) {
        mViewClazz = viewClazz;
    }

    void attach(V view) {
        mView = view;
        publish();
    }

    void detach() {
        this.mView = NullObject.create(mViewClazz);
    }

    // Overridden in subclasses to be performed after the presenter is attached
    // Also called in subclasses following data being fetched
    protected void publish() {

    }

    // Overriden in subclasses
    protected void onDestroyed() {

    }

}
