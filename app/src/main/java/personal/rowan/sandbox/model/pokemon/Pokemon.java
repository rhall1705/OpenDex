
package personal.rowan.sandbox.model.pokemon;

import java.util.List;

import personal.rowan.sandbox.util.PokemonUtil;

public class Pokemon {

    private List<Form> forms = null;
    private List<Ability> abilities = null;
    private List<Stat> stats = null;
    private String name;
    private Integer weight;
    private Sprites sprites;
    private List<Object> held_items = null;
    private String location_area_encounters;
    private Integer height;
    private Boolean is_default;
    private Species species;
    private Integer id;
    private Integer order;
    private Integer base_experience;
    private List<Type> types = null;

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
     *     The stats
     */
    public List<Stat> getStats() {
        return stats;
    }

    public Stat getSPD() {
        return stats.get(0);
    }

    public Stat getSPDEF() {
        return stats.get(1);
    }

    public Stat getSPATK() {
        return stats.get(2);
    }

    public Stat getDEF() {
        return stats.get(3);
    }

    public Stat getATK() {
        return stats.get(4);
    }

    public Stat getHP() {
        return stats.get(5);
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
     *     The weight
     */
    public Integer getWeight() {
        return weight;
    }

    public String getWeightString() {
        return String.valueOf(weight);
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
     *     The sprites
     */
    public Sprites getSprites() {
        return sprites;
    }

    /**
     * 
     * @param sprites
     *     The sprites
     */
    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    /**
     * 
     * @return
     *     The held_items
     */
    public List<Object> getHeldItems() {
        return held_items;
    }

    /**
     * 
     * @param heldItems
     *     The held_items
     */
    public void setHeldItems(List<Object> heldItems) {
        this.held_items = heldItems;
    }

    /**
     * 
     * @return
     *     The location_area_encounters
     */
    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    /**
     * 
     * @param location_area_encounters
     *     The location_area_encounters
     */
    public void setLocation_area_encounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
    }

    public String getHeightString() {
        return String.valueOf(height);
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
     *     The is_default
     */
    public Boolean getIsDefault() {
        return is_default;
    }

    /**
     * 
     * @param isDefault
     *     The is_default
     */
    public void setIsDefault(Boolean isDefault) {
        this.is_default = isDefault;
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
     *     The base_experience
     */
    public Integer getBaseExperience() {
        return base_experience;
    }

    /**
     * 
     * @param baseExperience
     *     The base_experience
     */
    public void setBaseExperience(Integer baseExperience) {
        this.base_experience = baseExperience;
    }

    /**
     * 
     * @return
     *     The types
     */
    public List<Type> getTypes() {
        return types;
    }

    public String getFormattedType() {
        if(types == null || types.isEmpty()) {
            return "";
        }
        String type1 = types.get(0).getType().getName();
        if(types.size() <= 1) {
            return PokemonUtil.capitalizeWord(type1);
        } else {
            return PokemonUtil.capitalizeWord(types.get(1).getType().getName()) + "/" + PokemonUtil.capitalizeWord(type1);
        }
    }

    /**
     * 
     * @param types
     *     The types
     */
    public void setTypes(List<Type> types) {
        this.types = types;
    }

}
