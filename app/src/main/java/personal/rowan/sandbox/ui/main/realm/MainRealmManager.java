package personal.rowan.sandbox.ui.main.realm;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import personal.rowan.sandbox.ui.main.MainViewModel;
import personal.rowan.sandbox.ui.main.dagger.MainScope;
import rx.Observable;

@MainScope
public class MainRealmManager {

    private Realm mRealm;

    @Inject
    public MainRealmManager(Realm realm) {
        mRealm = realm;
    }

    public Observable<List<MainViewModel>> load() {
        mRealm.where(RealmMainViewModel.class).findAll();

        return Observable.just(mRealm.copyFromRealm(mRealm.where(RealmMainViewModel.class).findAll()))
                .map(realmResults -> {
                        List<MainViewModel> viewModels = new ArrayList<>();
                        for(RealmMainViewModel realmResult : realmResults) {
                            viewModels.add(new MainViewModel(realmResult));
                        }
                        return viewModels;
                    }
                );
    }

    public void update(List<MainViewModel> viewModels) {
        mRealm.beginTransaction();
        for(MainViewModel viewModel : viewModels) {
            mRealm.copyToRealmOrUpdate(new RealmMainViewModel(viewModel));
        }
        mRealm.commitTransaction();
    }

    public void clear() {
        mRealm.beginTransaction();
        mRealm.allObjects(RealmMainViewModel.class).clear();
        mRealm.commitTransaction();
    }

    public void close() {
        mRealm.close();
    }

}
