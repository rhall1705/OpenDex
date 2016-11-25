package personal.rowan.sandbox.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import personal.rowan.sandbox.ui.activity.MainActivity;
import personal.rowan.sandbox.dagger.module.AppModule;
import personal.rowan.sandbox.dagger.module.PokeApiModule;

/**
 * Created by Rowan Hall
 */

@Singleton
@Component(modules = {AppModule.class, PokeApiModule.class})
public interface NetworkComponent {

    void inject(MainActivity activity);

}
