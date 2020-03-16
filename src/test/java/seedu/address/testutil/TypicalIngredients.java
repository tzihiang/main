package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALMOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_ALMOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ingredient.Ingredient;

/**
 * A utility class containing a list of {@code Ingredient} objects to be used in tests.
 */
public class TypicalIngredients {

    public static final Ingredient APPLE = new IngredientBuilder().withName("Apples").withQuantity("5").build();
    public static final Ingredient BUTTER = new IngredientBuilder().withName("Butter").withQuantity("200 g").build();
    public static final Ingredient CHICKEN = new IngredientBuilder().withName("Chicken").withQuantity("600 g").build();
    public static final Ingredient DUCK = new IngredientBuilder().withName("Duck").withQuantity("800 g").build();
    public static final Ingredient EGG = new IngredientBuilder().withName("Eggs").withQuantity("12").build();
    public static final Ingredient FLOUR = new IngredientBuilder().withName("Flour").withQuantity("500 g").build();
    public static final Ingredient GARLIC = new IngredientBuilder().withName("Garlic").withQuantity("3 cloves").build();

    // Manually added
    public static final Ingredient HAZELNUT = new IngredientBuilder().withName("Hazelnuts").build();
    public static final Ingredient ICECUBES = new IngredientBuilder().withName("Ice cubes").build();

    // Manually added - Ingredient's details found in {@code CommandTestUtil}
    public static final Ingredient ALMOND = new IngredientBuilder().withName(VALID_NAME_ALMOND)
            .withQuantity(VALID_QUANTITY_ALMOND).build();
    public static final Ingredient BANANA = new IngredientBuilder().withName(VALID_NAME_BANANA)
            .withQuantity(VALID_QUANTITY_BANANA).build();

    public static final String KEYWORD_MATCHING_APPLE = "Apple"; // A keyword that matches APPLE

    private TypicalIngredients() {} // prevents instantiation

    public static List<Ingredient> getTypicalIngredients() {
        return new ArrayList<>(Arrays.asList(APPLE, BUTTER, CHICKEN, DUCK, EGG, FLOUR, GARLIC));
    }
}
