package personal.rowan.sandbox.ui.detail2.dagger;

import dagger.Component;
import personal.rowan.sandbox.application.App;
import personal.rowan.sandbox.application.dagger.component.ApplicationComponent;
import personal.rowan.sandbox.application.dagger.module.PokeApiModule;
import personal.rowan.sandbox.ui.detail2.DetailActivity2;
import rx.functions.Action1;

/**
 * Created by Rowan Hall
 */

@DetailScope2
@Component(modules = { PokeApiModule.class }, dependencies = { ApplicationComponent.class })
public interface DetailComponent2 {

    void inject(DetailActivity2 detailActivity);

    Action1<DetailActivity2> injector = (activity) -> {
        DaggerDetailComponent2
                .builder()
                .applicationComponent(App.applicationComponent(activity))
                .build()
                .inject(activity);
    };

}
