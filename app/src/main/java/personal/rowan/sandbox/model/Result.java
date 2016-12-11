package personal.rowan.sandbox.model;

import personal.rowan.sandbox.util.PokemonUtil;

public class Result {

    private String url;
    private String name;
    private Integer number;

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getFormattedName() {
        return PokemonUtil.formatName(name);
    }

    public String getFormattedNumber() {
        return PokemonUtil.formatNumber(number);
    }

    public String getModelUrl() {
        return PokemonUtil.buildPokemonModelUrl(name);
    }

}
