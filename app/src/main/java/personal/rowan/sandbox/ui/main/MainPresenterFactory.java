package personal.rowan.sandbox.ui.main;

import personal.rowan.sandbox.ui.PresenterFactory;

/**
 * Created by Rowan Hall
 */

class MainPresenterFactory
        implements PresenterFactory<MainPresenter> {

    @Override
    public MainPresenter create() {
        return new MainPresenter();
    }

}
