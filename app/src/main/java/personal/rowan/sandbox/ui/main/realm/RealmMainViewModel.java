package personal.rowan.sandbox.ui.main.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import personal.rowan.sandbox.ui.main.MainViewModel;

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

    public String getName() {
        return mName;
    }

    public Integer getNumber() {
        return mNumber;
    }

    public String getFormattedName() {
        return mFormattedName;
    }

    public String getFormattedNumber() {
        return mFormattedNumber;
    }

    public String getModelUrl() {
        return mModelUrl;
    }

}
