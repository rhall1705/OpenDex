package personal.rowan.sandbox.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import personal.rowan.sandbox.dagger.module.ApplicationModule;
import personal.rowan.sandbox.dagger.module.NetworkModule;
import personal.rowan.sandbox.ui.detail.DetailPresenter;
import personal.rowan.sandbox.ui.main.MainPresenter;

/**
 * Created by Rowan Hall
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface NetworkComponent {

    void inject(MainPresenter mainPresenter);
    void inject(DetailPresenter detailPresenter);

}
