package personal.rowan.sandbox.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import personal.rowan.sandbox.ui.main.MainActivity;
import personal.rowan.sandbox.dagger.module.AppModule;
import personal.rowan.sandbox.dagger.module.PokeApiModule;
import personal.rowan.sandbox.ui.main.MainPresenter;

/**
 * Created by Rowan Hall
 */

@Singleton
@Component(modules = {AppModule.class, PokeApiModule.class})
public interface NetworkComponent {

    void inject(MainPresenter mainPresenter);

}
