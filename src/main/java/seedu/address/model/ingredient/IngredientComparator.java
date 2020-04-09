package seedu.address.model.ingredient;

import java.util.Comparator;

/**
 * A custom comparator for Ingredient
 */
public class IngredientComparator implements Comparator<Ingredient> {

    /**
     * compare ingredient names using lexical ordering
     */
    @Override
    public int compare(Ingredient o1, Ingredient o2) {
        return o1.getName().toString().compareToIgnoreCase(o2.getName().toString());
    }
}
