package personal.rowan.sandbox.ui.detail;

import personal.rowan.sandbox.ui.base.presenter.PresenterFactory;

/**
 * Created by Rowan Hall
 */

class DetailPresenterFactory
        implements PresenterFactory<DetailPresenter> {

    private String mName;

    DetailPresenterFactory(String name) {
        mName = name;
    }

    @Override
    public DetailPresenter create() {
        return new DetailPresenter(mName);
    }

}
