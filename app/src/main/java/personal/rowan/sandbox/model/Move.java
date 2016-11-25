
package personal.rowan.sandbox.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Move {

    private Move_ move;
    private List<VersionGroupDetail> versionGroupDetails = new ArrayList<VersionGroupDetail>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The move
     */
    public Move_ getMove() {
        return move;
    }

    /**
     * 
     * @param move
     *     The move
     */
    public void setMove(Move_ move) {
        this.move = move;
    }

    /**
     * 
     * @return
     *     The versionGroupDetails
     */
    public List<VersionGroupDetail> getVersionGroupDetails() {
        return versionGroupDetails;
    }

    /**
     * 
     * @param versionGroupDetails
     *     The version_group_details
     */
    public void setVersionGroupDetails(List<VersionGroupDetail> versionGroupDetails) {
        this.versionGroupDetails = versionGroupDetails;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
