package personal.rowan.sandbox.ui.main.realm;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import personal.rowan.sandbox.ui.main.MainViewModel;
import personal.rowan.sandbox.ui.main.dagger.MainScope;
import rx.Observable;

@MainScope
public class MainViewModelRealmManager {

    private Realm mRealm;

    @Inject
    public MainViewModelRealmManager(Realm realm) {
        mRealm = realm;
    }

    public Observable<List<MainViewModel>> load() {
        return Observable.just(mRealm.copyFromRealm(mRealm.allObjects(RealmMainViewModel.class)))
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
