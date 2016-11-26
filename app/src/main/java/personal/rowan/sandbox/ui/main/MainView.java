package personal.rowan.sandbox.ui.main;

import java.util.List;

import personal.rowan.sandbox.model.Result;

/**
 * Created by Rowan Hall
 */

interface MainView {

    void displayData(List<Result> data);

    void onError(Throwable e);

    void showProgress();

    void hideProgress();

}
