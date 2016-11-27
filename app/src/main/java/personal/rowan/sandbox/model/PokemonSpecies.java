
package personal.rowan.sandbox.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PokemonSpecies {

    private Integer captureRate;
    private Habitat habitat;
    private Color color;
    private Boolean formsSwitchable;
    private Shape shape;
    private List<Name> names = new ArrayList<Name>();
    private Integer id;
    private List<EggGroup> eggGroups = new ArrayList<EggGroup>();
    private Integer baseHappiness;
    private Generation generation;
    private List<FlavorTextEntry> flavorTextEntries = new ArrayList<FlavorTextEntry>();
    private GrowthRate growthRate;
    private Integer hatchCounter;
    private List<Genera> genera = new ArrayList<Genera>();
    private Object evolvesFromSpecies;
    private List<Object> formDescriptions = new ArrayList<Object>();
    private List<Variety> varieties = new ArrayList<Variety>();
    private String name;
    private EvolutionChain evolutionChain;
    private Boolean hasGenderDifferences;
    private Boolean isBaby;
    private Integer genderRate;
    private List<PalParkEncounter> palParkEncounters = new ArrayList<PalParkEncounter>();
    private Integer order;
    private List<PokedexNumber> pokedexNumbers = new ArrayList<PokedexNumber>();

    /**
     * 
     * @return
     *     The captureRate
     */
    public Integer getCaptureRate() {
        return captureRate;
    }

    /**
     * 
     * @param captureRate
     *     The capture_rate
     */
    public void setCaptureRate(Integer captureRate) {
        this.captureRate = captureRate;
    }

    /**
     * 
     * @return
     *     The habitat
     */
    public Habitat getHabitat() {
        return habitat;
    }

    /**
     * 
     * @param habitat
     *     The habitat
     */
    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    /**
     * 
     * @return
     *     The color
     */
    public Color getColor() {
        return color;
    }

    /**
     * 
     * @param color
     *     The color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * 
     * @return
     *     The formsSwitchable
     */
    public Boolean getFormsSwitchable() {
        return formsSwitchable;
    }

    /**
     * 
     * @param formsSwitchable
     *     The forms_switchable
     */
    public void setFormsSwitchable(Boolean formsSwitchable) {
        this.formsSwitchable = formsSwitchable;
    }

    /**
     * 
     * @return
     *     The shape
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * 
     * @param shape
     *     The shape
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * 
     * @return
     *     The names
     */
    public List<Name> getNames() {
        return names;
    }

    /**
     * 
     * @param names
     *     The names
     */
    public void setNames(List<Name> names) {
        this.names = names;
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
     *     The eggGroups
     */
    public List<EggGroup> getEggGroups() {
        return eggGroups;
    }

    /**
     * 
     * @param eggGroups
     *     The egg_groups
     */
    public void setEggGroups(List<EggGroup> eggGroups) {
        this.eggGroups = eggGroups;
    }

    /**
     * 
     * @return
     *     The baseHappiness
     */
    public Integer getBaseHappiness() {
        return baseHappiness;
    }

    /**
     * 
     * @param baseHappiness
     *     The base_happiness
     */
    public void setBaseHappiness(Integer baseHappiness) {
        this.baseHappiness = baseHappiness;
    }

    /**
     * 
     * @return
     *     The generation
     */
    public Generation getGeneration() {
        return generation;
    }

    /**
     * 
     * @param generation
     *     The generation
     */
    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    /**
     * 
     * @return
     *     The flavorTextEntries
     */
    public List<FlavorTextEntry> getFlavorTextEntries() {
        return flavorTextEntries;
    }

    /**
     * 
     * @param flavorTextEntries
     *     The flavor_text_entries
     */
    public void setFlavorTextEntries(List<FlavorTextEntry> flavorTextEntries) {
        this.flavorTextEntries = flavorTextEntries;
    }

    /**
     * 
     * @return
     *     The growthRate
     */
    public GrowthRate getGrowthRate() {
        return growthRate;
    }

    /**
     * 
     * @param growthRate
     *     The growth_rate
     */
    public void setGrowthRate(GrowthRate growthRate) {
        this.growthRate = growthRate;
    }

    /**
     * 
     * @return
     *     The hatchCounter
     */
    public Integer getHatchCounter() {
        return hatchCounter;
    }

    /**
     * 
     * @param hatchCounter
     *     The hatch_counter
     */
    public void setHatchCounter(Integer hatchCounter) {
        this.hatchCounter = hatchCounter;
    }

    /**
     * 
     * @return
     *     The genera
     */
    public List<Genera> getGenera() {
        return genera;
    }

    /**
     * 
     * @param genera
     *     The genera
     */
    public void setGenera(List<Genera> genera) {
        this.genera = genera;
    }

    /**
     * 
     * @return
     *     The evolvesFromSpecies
     */
    public Object getEvolvesFromSpecies() {
        return evolvesFromSpecies;
    }

    /**
     * 
     * @param evolvesFromSpecies
     *     The evolves_from_species
     */
    public void setEvolvesFromSpecies(Object evolvesFromSpecies) {
        this.evolvesFromSpecies = evolvesFromSpecies;
    }

    /**
     * 
     * @return
     *     The formDescriptions
     */
    public List<Object> getFormDescriptions() {
        return formDescriptions;
    }

    /**
     * 
     * @param formDescriptions
     *     The form_descriptions
     */
    public void setFormDescriptions(List<Object> formDescriptions) {
        this.formDescriptions = formDescriptions;
    }

    /**
     * 
     * @return
     *     The varieties
     */
    public List<Variety> getVarieties() {
        return varieties;
    }

    /**
     * 
     * @param varieties
     *     The varieties
     */
    public void setVarieties(List<Variety> varieties) {
        this.varieties = varieties;
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
     *     The evolutionChain
     */
    public EvolutionChain getEvolutionChain() {
        return evolutionChain;
    }

    /**
     * 
     * @param evolutionChain
     *     The evolution_chain
     */
    public void setEvolutionChain(EvolutionChain evolutionChain) {
        this.evolutionChain = evolutionChain;
    }

    /**
     * 
     * @return
     *     The hasGenderDifferences
     */
    public Boolean getHasGenderDifferences() {
        return hasGenderDifferences;
    }

    /**
     * 
     * @param hasGenderDifferences
     *     The has_gender_differences
     */
    public void setHasGenderDifferences(Boolean hasGenderDifferences) {
        this.hasGenderDifferences = hasGenderDifferences;
    }

    /**
     * 
     * @return
     *     The isBaby
     */
    public Boolean getIsBaby() {
        return isBaby;
    }

    /**
     * 
     * @param isBaby
     *     The is_baby
     */
    public void setIsBaby(Boolean isBaby) {
        this.isBaby = isBaby;
    }

    /**
     * 
     * @return
     *     The genderRate
     */
    public Integer getGenderRate() {
        return genderRate;
    }

    /**
     * 
     * @param genderRate
     *     The gender_rate
     */
    public void setGenderRate(Integer genderRate) {
        this.genderRate = genderRate;
    }

    /**
     * 
     * @return
     *     The palParkEncounters
     */
    public List<PalParkEncounter> getPalParkEncounters() {
        return palParkEncounters;
    }

    /**
     * 
     * @param palParkEncounters
     *     The pal_park_encounters
     */
    public void setPalParkEncounters(List<PalParkEncounter> palParkEncounters) {
        this.palParkEncounters = palParkEncounters;
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
     *     The pokedexNumbers
     */
    public List<PokedexNumber> getPokedexNumbers() {
        return pokedexNumbers;
    }

    /**
     * 
     * @param pokedexNumbers
     *     The pokedex_numbers
     */
    public void setPokedexNumbers(List<PokedexNumber> pokedexNumbers) {
        this.pokedexNumbers = pokedexNumbers;
    }

}
