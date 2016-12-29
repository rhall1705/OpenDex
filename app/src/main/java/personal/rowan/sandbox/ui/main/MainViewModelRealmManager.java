package personal.rowan.sandbox.ui.main;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import personal.rowan.sandbox.ui.main.dagger.MainScope;
import rx.Observable;

@MainScope
class MainViewModelRealmManager {

    private Realm mRealm;

    @Inject
    MainViewModelRealmManager(Realm realm) {
        mRealm = realm;
    }

    Observable<List<MainViewModel>> load() {
        return Observable.just(mRealm.copyFromRealm(mRealm.allObjects(RealmMainViewModel.class)))
                .flatMap(realmResults -> {
                        List<MainViewModel> viewModels = new ArrayList<>();
                        for(RealmMainViewModel realmResult : realmResults) {
                            viewModels.add(new MainViewModel(realmResult));
                        }
                        return Observable.just(viewModels);
                    }
                );
    }

    void update(List<MainViewModel> viewModels) {
        mRealm.beginTransaction();
        for(MainViewModel viewModel : viewModels) {
            mRealm.copyToRealmOrUpdate(new RealmMainViewModel(viewModel));
        }
        mRealm.commitTransaction();
    }

    void clear() {
        mRealm.beginTransaction();
        mRealm.allObjects(RealmMainViewModel.class).clear();
        mRealm.commitTransaction();
    }

    void close() {
        mRealm.close();
    }

}
