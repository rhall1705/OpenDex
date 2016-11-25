
package personal.rowan.sandbox.model;

import java.util.HashMap;
import java.util.Map;

public class GameIndex {

    private Integer gameIndex;
    private Version version;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The gameIndex
     */
    public Integer getGameIndex() {
        return gameIndex;
    }

    /**
     * 
     * @param gameIndex
     *     The game_index
     */
    public void setGameIndex(Integer gameIndex) {
        this.gameIndex = gameIndex;
    }

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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
