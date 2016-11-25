
package personal.rowan.sandbox.model;

import java.util.HashMap;
import java.util.Map;

public class VersionGroupDetail {

    private Integer levelLearnedAt;
    private VersionGroup versionGroup;
    private MoveLearnMethod moveLearnMethod;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The levelLearnedAt
     */
    public Integer getLevelLearnedAt() {
        return levelLearnedAt;
    }

    /**
     * 
     * @param levelLearnedAt
     *     The level_learned_at
     */
    public void setLevelLearnedAt(Integer levelLearnedAt) {
        this.levelLearnedAt = levelLearnedAt;
    }

    /**
     * 
     * @return
     *     The versionGroup
     */
    public VersionGroup getVersionGroup() {
        return versionGroup;
    }

    /**
     * 
     * @param versionGroup
     *     The version_group
     */
    public void setVersionGroup(VersionGroup versionGroup) {
        this.versionGroup = versionGroup;
    }

    /**
     * 
     * @return
     *     The moveLearnMethod
     */
    public MoveLearnMethod getMoveLearnMethod() {
        return moveLearnMethod;
    }

    /**
     * 
     * @param moveLearnMethod
     *     The move_learn_method
     */
    public void setMoveLearnMethod(MoveLearnMethod moveLearnMethod) {
        this.moveLearnMethod = moveLearnMethod;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
