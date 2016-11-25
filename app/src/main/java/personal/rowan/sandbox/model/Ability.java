
package personal.rowan.sandbox.model;

import java.util.HashMap;
import java.util.Map;

public class Ability {

    private Boolean isHidden;
    private Integer slot;
    private Ability_ ability;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The isHidden
     */
    public Boolean getIsHidden() {
        return isHidden;
    }

    /**
     * 
     * @param isHidden
     *     The is_hidden
     */
    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    /**
     * 
     * @return
     *     The slot
     */
    public Integer getSlot() {
        return slot;
    }

    /**
     * 
     * @param slot
     *     The slot
     */
    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    /**
     * 
     * @return
     *     The ability
     */
    public Ability_ getAbility() {
        return ability;
    }

    /**
     * 
     * @param ability
     *     The ability
     */
    public void setAbility(Ability_ ability) {
        this.ability = ability;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
