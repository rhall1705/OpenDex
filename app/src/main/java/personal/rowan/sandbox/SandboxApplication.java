package personal.rowan.sandbox;

import android.app.Application;

import personal.rowan.sandbox.dagger.component.DaggerNetworkComponent;
import personal.rowan.sandbox.dagger.component.NetworkComponent;
import personal.rowan.sandbox.dagger.module.AppModule;
import personal.rowan.sandbox.dagger.module.PokeApiModule;
import personal.rowan.sandbox.network.PokemonService;

/**
 * Created by Rowan Hall
 */

public class SandboxApplication
        extends Application {

    private static SandboxApplication sInstance;

    private NetworkComponent mNetworkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mNetworkComponent = DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .pokeApiModule(new PokeApiModule(PokemonService.BASE_URL))
                .build();
    }

    public static synchronized SandboxApplication getInstance() {
        return sInstance;
    }

    public NetworkComponent networkComponent() {
        return mNetworkComponent;
    }

}
