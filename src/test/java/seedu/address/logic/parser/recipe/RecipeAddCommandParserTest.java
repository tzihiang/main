package seedu.address.logic.parser.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STEP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STEP_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.recipe.RecipeAddIngredientCommand;
import seedu.address.logic.commands.recipe.RecipeAddStepCommand;
import seedu.address.logic.commands.recipe.RecipeAddTagCommand;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;
import seedu.address.model.step.Step;
import seedu.address.model.tag.Tag;

public class RecipeAddCommandParserTest {
    private static final Index VALID_RECIPE_INDEX = new Index(0);
    private static final Ingredient VALID_INGREDIENT = new Ingredient(new IngredientName("Ingredient"),
        new IngredientQuantity("5"));
    private static final String VALID_ADD_INGREDIENT_ARGUMENT = "1 i/Ingredient q/5";
    private static final String INVALID_ADD_INGREDIENT_ARGUMENT_NO_RECIPE_INDEX = "i/Ingredient q/5";
    private static final String INVALID_ADD_INGREDIENT_ARGUMENT_NO_INGREDIENT_NAME = "1 q/5";

    private static final Step VALID_STEP = new Step("Step");
    private static final Index VALID_STEP_INDEX = new Index(0);
    private static final String VALID_ADD_STEP_ARGUMENT = "1 x/1 s/Step";
    private static final String INVALID_ADD_STEP_ARGUMENT_NO_RECIPE_INDEX = "x/1 s/Step";
    private static final String INVALID_ADD_STEP_ARGUMENT_NO_STEP_INDEX = "1 x/s";
    private static final String INVALID_ADD_STEP_ARGUMENT_NO_STEP_DESCRIPTION = "1 s/Step";

    private static final Tag VALID_TAG = new Tag("Tag");
    private static final String VALID_ADD_TAG_ARGUMENT = "1 t/Tag";
    private static final String INVALID_ADD_TAG_ARGUMENT_NO_RECIPE_INDEX = "t/Tag";
    private static final String INVALID_ADD_TAG_ARGUMENT_NO_TAG = "1";

    private static final String INVALID_ARGUMENT = "Invalid argument";

    @Test
    public void parse_validInput() throws ParseException {
        assertEquals(new RecipeAddCommandParser().parse(VALID_ADD_INGREDIENT_ARGUMENT),
            new RecipeAddIngredientCommand(VALID_RECIPE_INDEX, VALID_INGREDIENT));
        assertEquals(new RecipeAddCommandParser().parse(VALID_ADD_STEP_ARGUMENT),
            new RecipeAddStepCommand(VALID_RECIPE_INDEX, VALID_STEP_INDEX, VALID_STEP));
        assertEquals(new RecipeAddCommandParser().parse(VALID_ADD_TAG_ARGUMENT),
            new RecipeAddTagCommand(VALID_RECIPE_INDEX, VALID_TAG));
    }

    @Test
    public void parse_invalidInput() {
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parse(INVALID_ADD_INGREDIENT_ARGUMENT_NO_RECIPE_INDEX));
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parse(INVALID_ADD_INGREDIENT_ARGUMENT_NO_INGREDIENT_NAME));
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parse(INVALID_ADD_STEP_ARGUMENT_NO_RECIPE_INDEX));
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parse(INVALID_ADD_STEP_ARGUMENT_NO_STEP_INDEX));
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parse(INVALID_ADD_STEP_ARGUMENT_NO_STEP_DESCRIPTION));
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parse(INVALID_ADD_TAG_ARGUMENT_NO_TAG));
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parse(INVALID_ADD_TAG_ARGUMENT_NO_RECIPE_INDEX));
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parse(INVALID_ARGUMENT));
    }

    @Test
    public void parseAddIngredient_validInput() throws ParseException {
        assertEquals(new RecipeAddCommandParser().parseAddIngredient(VALID_ADD_INGREDIENT_ARGUMENT),
            new RecipeAddIngredientCommand(VALID_RECIPE_INDEX, VALID_INGREDIENT));
    }

    @Test
    public void parseAddIngredient_invalidInput() {
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parseAddIngredient(INVALID_ADD_INGREDIENT_ARGUMENT_NO_RECIPE_INDEX));
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parseAddIngredient(INVALID_ADD_INGREDIENT_ARGUMENT_NO_INGREDIENT_NAME));
    }

    @Test
    public void parseAddStep_validInput() throws ParseException {
        assertEquals(new RecipeAddCommandParser().parseAddStep(VALID_ADD_STEP_ARGUMENT),
            new RecipeAddStepCommand(VALID_RECIPE_INDEX, VALID_STEP_INDEX, VALID_STEP));
    }

    @Test
    public void parseAddStep_invalidInput() {
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parseAddStep(INVALID_ADD_STEP_ARGUMENT_NO_RECIPE_INDEX));
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parseAddIngredient(INVALID_ADD_STEP_ARGUMENT_NO_STEP_INDEX));
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parseAddIngredient(INVALID_ADD_STEP_ARGUMENT_NO_STEP_DESCRIPTION));
    }

    @Test
    public void parseAddTag_validInput() throws ParseException {
        assertEquals(new RecipeAddCommandParser().parseAddTag(VALID_ADD_TAG_ARGUMENT),
            new RecipeAddTagCommand(VALID_RECIPE_INDEX, VALID_TAG));
    }

    @Test
    public void parseAddTag_invalidInput() {
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parseAddTag(INVALID_ADD_TAG_ARGUMENT_NO_RECIPE_INDEX));
        assertThrows(ParseException.class, () ->
            new RecipeAddCommandParser().parseAddTag(INVALID_ADD_TAG_ARGUMENT_NO_TAG));
    }

    @Test
    public void containsIngredient_validInput() {
        assertTrue(new RecipeAddCommandParser().containsIngredient(VALID_ADD_INGREDIENT_ARGUMENT));
        assertFalse(new RecipeAddCommandParser()
            .containsIngredient(INVALID_ADD_INGREDIENT_ARGUMENT_NO_INGREDIENT_NAME));
    }

    @Test
    public void containsStep_validInput() {
        assertTrue(new RecipeAddCommandParser().containsStep(VALID_ADD_STEP_ARGUMENT));
        assertFalse(new RecipeAddCommandParser().containsStep(INVALID_ADD_STEP_ARGUMENT_NO_STEP_DESCRIPTION));
        assertFalse(new RecipeAddCommandParser().containsStep(INVALID_ADD_STEP_ARGUMENT_NO_STEP_INDEX));
    }

    @Test
    public void containsTag_validInput() {
        assertTrue(new RecipeAddCommandParser().containsTag(VALID_ADD_TAG_ARGUMENT));
        assertFalse(new RecipeAddCommandParser().containsTag(INVALID_ADD_TAG_ARGUMENT_NO_TAG));
    }

    @Test
    public void arePrefixesPresent_validInput() {
        assertTrue(RecipeAddCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
            VALID_ADD_INGREDIENT_ARGUMENT, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY),
            PREFIX_INGREDIENT_NAME));
        assertFalse(RecipeAddCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
            INVALID_ADD_INGREDIENT_ARGUMENT_NO_INGREDIENT_NAME, PREFIX_INGREDIENT_NAME,
            PREFIX_INGREDIENT_QUANTITY), PREFIX_INGREDIENT_NAME));

        assertTrue(RecipeAddCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
            VALID_ADD_STEP_ARGUMENT, PREFIX_STEP_INDEX, PREFIX_STEP_DESCRIPTION),
            PREFIX_STEP_INDEX, PREFIX_STEP_DESCRIPTION));
        assertFalse(RecipeAddCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
            INVALID_ADD_STEP_ARGUMENT_NO_STEP_DESCRIPTION, PREFIX_STEP_INDEX, PREFIX_STEP_DESCRIPTION),
            PREFIX_STEP_INDEX, PREFIX_STEP_DESCRIPTION));
        assertFalse(RecipeAddCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
            INVALID_ADD_STEP_ARGUMENT_NO_STEP_INDEX, PREFIX_STEP_INDEX, PREFIX_STEP_DESCRIPTION),
            PREFIX_STEP_INDEX, PREFIX_STEP_DESCRIPTION));

        assertTrue(RecipeAddCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
            VALID_ADD_TAG_ARGUMENT, PREFIX_TAG, PREFIX_STEP_DESCRIPTION), PREFIX_TAG));
        assertFalse(RecipeAddCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
            INVALID_ADD_TAG_ARGUMENT_NO_TAG, PREFIX_TAG, PREFIX_STEP_DESCRIPTION), PREFIX_TAG));
    }
}
