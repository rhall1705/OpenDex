package personal.rowan.sandbox;

import android.app.Application;

import personal.rowan.sandbox.dagger.component.DaggerNetworkComponent;
import personal.rowan.sandbox.dagger.component.NetworkComponent;
import personal.rowan.sandbox.dagger.module.ApplicationModule;
import personal.rowan.sandbox.dagger.module.NetworkModule;
import personal.rowan.sandbox.network.PokemonService;

/**
 * Created by Rowan Hall
 */

public class SandboxApplication
        extends Application {

    private static SandboxApplication sInstance;

    private NetworkComponent mPokeApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mPokeApiComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(PokemonService.BASE_URL))
                .build();
    }

    public static synchronized SandboxApplication getInstance() {
        return sInstance;
    }

    public NetworkComponent pokeApiComponent() {
        return mPokeApiComponent;
    }

}
