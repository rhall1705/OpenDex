package personal.rowan.sandbox.ui.main;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmMainViewModel 
        extends RealmObject {

    private String mName;
    @PrimaryKey private Integer mNumber;
    private String mFormattedName;
    private String mFormattedNumber;
    private String mModelUrl;

    public RealmMainViewModel() {

    }

    RealmMainViewModel(MainViewModel viewModel) {
        mName = viewModel.getName();
        mNumber = viewModel.getNumber();
        mFormattedName = viewModel.getFormattedName();
        mFormattedNumber = viewModel.getFormattedNumber();
        mModelUrl = viewModel.getModelUrl();
    }

    String getName() {
        return mName;
    }

    Integer getNumber() {
        return mNumber;
    }

    String getFormattedName() {
        return mFormattedName;
    }

    String getFormattedNumber() {
        return mFormattedNumber;
    }

    String getModelUrl() {
        return mModelUrl;
    }

}
