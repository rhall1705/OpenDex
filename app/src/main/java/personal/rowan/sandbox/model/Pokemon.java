
package personal.rowan.sandbox.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pokemon {

    private Integer id;
    private String name;
    private Integer baseExperience;
    private Integer height;
    private Boolean isDefault;
    private Integer order;
    private Integer weight;
    private List<Ability> abilities = new ArrayList<Ability>();
    private List<Form> forms = new ArrayList<Form>();
    private List<GameIndex> gameIndices = new ArrayList<GameIndex>();
    private List<Object> heldItems = new ArrayList<Object>();
    private List<Object> locationAreaEncounters = new ArrayList<Object>();
    private List<Move> moves = new ArrayList<Move>();
    private Species species;
    private List<Stat> stats = new ArrayList<Stat>();
    private List<Type> types = new ArrayList<Type>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
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

    /**
     * 
     * @return
     *     The baseExperience
     */
    public Integer getBaseExperience() {
        return baseExperience;
    }

    /**
     * 
     * @param baseExperience
     *     The base_experience
     */
    public void setBaseExperience(Integer baseExperience) {
        this.baseExperience = baseExperience;
    }

    /**
     * 
     * @return
     *     The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The isDefault
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * 
     * @param isDefault
     *     The is_default
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 
     * @return
     *     The order
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 
     * @param order
     *     The order
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 
     * @return
     *     The weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 
     * @param weight
     *     The weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 
     * @return
     *     The abilities
     */
    public List<Ability> getAbilities() {
        return abilities;
    }

    /**
     * 
     * @param abilities
     *     The abilities
     */
    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    /**
     * 
     * @return
     *     The forms
     */
    public List<Form> getForms() {
        return forms;
    }

    /**
     * 
     * @param forms
     *     The forms
     */
    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    /**
     * 
     * @return
     *     The gameIndices
     */
    public List<GameIndex> getGameIndices() {
        return gameIndices;
    }

    /**
     * 
     * @param gameIndices
     *     The game_indices
     */
    public void setGameIndices(List<GameIndex> gameIndices) {
        this.gameIndices = gameIndices;
    }

    /**
     * 
     * @return
     *     The heldItems
     */
    public List<Object> getHeldItems() {
        return heldItems;
    }

    /**
     * 
     * @param heldItems
     *     The held_items
     */
    public void setHeldItems(List<Object> heldItems) {
        this.heldItems = heldItems;
    }

    /**
     * 
     * @return
     *     The locationAreaEncounters
     */
    public List<Object> getLocationAreaEncounters() {
        return locationAreaEncounters;
    }

    /**
     * 
     * @param locationAreaEncounters
     *     The location_area_encounters
     */
    public void setLocationAreaEncounters(List<Object> locationAreaEncounters) {
        this.locationAreaEncounters = locationAreaEncounters;
    }

    /**
     * 
     * @return
     *     The moves
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * 
     * @param moves
     *     The moves
     */
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    /**
     * 
     * @return
     *     The species
     */
    public Species getSpecies() {
        return species;
    }

    /**
     * 
     * @param species
     *     The species
     */
    public void setSpecies(Species species) {
        this.species = species;
    }

    /**
     * 
     * @return
     *     The stats
     */
    public List<Stat> getStats() {
        return stats;
    }

    /**
     * 
     * @param stats
     *     The stats
     */
    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    /**
     * 
     * @return
     *     The types
     */
    public List<Type> getTypes() {
        return types;
    }

    /**
     * 
     * @param types
     *     The types
     */
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
