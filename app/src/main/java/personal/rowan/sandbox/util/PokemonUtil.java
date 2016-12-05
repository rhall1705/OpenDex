package personal.rowan.sandbox.util;

import java.util.ArrayList;
import java.util.List;

import personal.rowan.sandbox.model.Color;
import personal.rowan.sandbox.model.EggGroup;
import personal.rowan.sandbox.model.FlavorTextEntry;
import personal.rowan.sandbox.model.GrowthRate;
import personal.rowan.sandbox.model.Habitat;
import personal.rowan.sandbox.model.Language_;
import personal.rowan.sandbox.model.PokemonSpecies;
import personal.rowan.sandbox.model.Shape;
import personal.rowan.sandbox.model.Version;

/**
 * Created by Rowan Hall
 */

public class PokemonUtil {

    public static String createDetailString(PokemonSpecies pokemon) {
        if(pokemon == null) return "";

        StringBuilder b = new StringBuilder();

        Integer captureRate = pokemon.getCaptureRate();
        if(captureRate != null && captureRate > 0) {
            b.append("Capture Rate: ")
                    .append(captureRate)
                    .append("\n");
        }

        Habitat habitat = pokemon.getHabitat();
        if(habitat != null) {
            b.append("Habitat: ")
                    .append(habitat.getName())
                    .append("\n");
        }

        Color color = pokemon.getColor();
        if(color != null) {
            b.append("Color: ")
                    .append(color.getName())
                    .append("\n");
        }

        Shape shape = pokemon.getShape();
        if(shape != null) {
            b.append("Shape: ")
                    .append(shape.getName())
                    .append("\n");
        }

        List<EggGroup> eggGroups = pokemon.getEggGroups();
        if(eggGroups != null && !eggGroups.isEmpty()) {
            b.append("Egg Groups: ");
            int size = eggGroups.size();
            for(int i = 0; i < size; i++) {
                b.append(eggGroups.get(i).getName());
                if(i < size - 1) {
                    b.append(", ");
                }
            }
            b.append("\n");
        }

        Integer baseHappiness = pokemon.getBaseHappiness();
        if(baseHappiness != null) {
            b.append("Base Happiness: ")
                    .append(baseHappiness)
                    .append("\n");
        }

        GrowthRate growthRate = pokemon.getGrowthRate();
        if(growthRate != null) {
            b.append("Growth Rate: ")
                    .append(growthRate.getName())
                    .append("\n");
        }

        Integer hatchCounter = pokemon.getHatchCounter();
        if(hatchCounter != null) {
            b.append("Hatch Counter: ")
                    .append(hatchCounter)
                    .append("\n");
        }

        List<FlavorTextEntry> flavorTextEntries = getEnglishFlavorTextEntries(pokemon);
        if(!flavorTextEntries.isEmpty()) {
            b.append("\nFlavor: \n\n");
            for(FlavorTextEntry flavorTextEntry : flavorTextEntries) {
                Version version = flavorTextEntry.getVersion();
                if(version != null) {
                    b.append(capitalizeAllWords(version.getName().replace("-", " "))).append("\n")
                            .append(flavorTextEntry.getFlavorText().replace("\n", " "))
                            .append("\n\n");
                }
            }
        }

        return b.toString();
    }

    private static List<FlavorTextEntry> getEnglishFlavorTextEntries(PokemonSpecies pokemon) {
        List<FlavorTextEntry> englishEntries = new ArrayList<>();
        List<FlavorTextEntry> allEntries = pokemon.getFlavorTextEntries();
        if(allEntries == null || allEntries.isEmpty()) return englishEntries;
        for(FlavorTextEntry entry : allEntries) {
            Language_ language = entry.getLanguage();
            if(language != null && "en".equals(language.getName())) {
                englishEntries.add(entry);
            }
        }
        return englishEntries;
    }

    private static String capitalizeAllWords(String string) {
        StringBuilder b = new StringBuilder();
        String[] words = string.split(" ");
        for(String word : words) {
            b.append(capitalizeWord(word)).append(" ");
        }
        return b.toString();
    }

    public static String capitalizeWord(String string) {
        if(string == null || string.isEmpty()) return string;
        if(string.length() == 1) return string.toUpperCase();
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

}
