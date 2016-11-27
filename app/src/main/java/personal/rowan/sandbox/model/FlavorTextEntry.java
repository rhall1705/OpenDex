
package personal.rowan.sandbox.model;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class FlavorTextEntry {

    private Version version;
    private String flavorText;
    private Language_ language;

    /**
     * 
     * @return
     *     The version
     */
    public Version getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(Version version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The flavorText
     */
    public String getFlavorText() {
        return flavorText;
    }

    /**
     * 
     * @param flavorText
     *     The flavor_text
     */
    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    /**
     * 
     * @return
     *     The language
     */
    public Language_ getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    public void setLanguage(Language_ language) {
        this.language = language;
    }

}
