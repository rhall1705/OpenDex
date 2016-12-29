package personal.rowan.sandbox.ui.main;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import personal.rowan.sandbox.ui.main.dagger.MainScope;

@MainScope
class MainViewModelRealmManager {

    private Realm mRealm;

    @Inject
    MainViewModelRealmManager(Realm realm) {
        mRealm = realm;
    }

    List<MainViewModel> getAllViewModels() {
        RealmResults<RealmMainViewModel> realmResults = getRealmResults();
        List<MainViewModel> viewModels = new ArrayList<>();
        for(RealmMainViewModel realmResult : realmResults) {
            viewModels.add(new MainViewModel(realmResult));
        }
        return viewModels;
    }

    private RealmResults<RealmMainViewModel> getRealmResults() {
        return mRealm.allObjects(RealmMainViewModel.class);
    }

    void addViewModels(List<MainViewModel> viewModels) {
        mRealm.beginTransaction();
        for(MainViewModel viewModel : viewModels) {
            mRealm.copyToRealmOrUpdate(new RealmMainViewModel(viewModel));
        }
        mRealm.commitTransaction();
    }

    void close() {
        mRealm.close();
    }

}
