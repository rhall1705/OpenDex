
package personal.rowan.sandbox.model;

import java.util.HashMap;
import java.util.Map;

public class Stat {

    private Integer baseStat;
    private Integer effort;
    private Stat_ stat;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The baseStat
     */
    public Integer getBaseStat() {
        return baseStat;
    }

    /**
     * 
     * @param baseStat
     *     The base_stat
     */
    public void setBaseStat(Integer baseStat) {
        this.baseStat = baseStat;
    }

    /**
     * 
     * @return
     *     The effort
     */
    public Integer getEffort() {
        return effort;
    }

    /**
     * 
     * @param effort
     *     The effort
     */
    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    /**
     * 
     * @return
     *     The stat
     */
    public Stat_ getStat() {
        return stat;
    }

    /**
     * 
     * @param stat
     *     The stat
     */
    public void setStat(Stat_ stat) {
        this.stat = stat;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
