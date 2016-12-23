package personal.rowan.sandbox.ui.main;

import personal.rowan.sandbox.util.PokemonUtil;

/**
 * Created by Rowan Hall
 */

public class MainViewModel {

    private String mName;
    private Integer mNumber;
    private String mFormattedName;
    private String mFormattedNumber;
    private String mModelUrl;

    MainViewModel(String name, Integer number) {
        mName = name;
        mNumber = number;
        mFormattedName = PokemonUtil.formatName(mName);
        mFormattedNumber = PokemonUtil.formatNumber(mNumber);
        mModelUrl = PokemonUtil.buildPokemonModelUrl(mName);
    }

    String getName() {
        return mName;
    }

    Integer getNumber() {
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
