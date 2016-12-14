package personal.rowan.sandbox.ui.detail;

import personal.rowan.sandbox.util.PokemonUtil;

/**
 * Created by Rowan Hall
 */

public class PreloadedPokemon {

    private String mName;

    public PreloadedPokemon(String name) {
        mName = name;
    }

    public String getFormattedName() {
        return PokemonUtil.formatName(mName);
    }

    public String getArtworkUrl() {
        return PokemonUtil.buildPokemonArtworkUrl(mName);
    }

    public String getModelUrl() {
        return PokemonUtil.buildPokemonModelUrl(mName);
    }

}
