package seedu.address.logic.parser.cart;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;

import java.util.stream.Stream;

import seedu.address.logic.commands.cart.CartAddCommand;
import seedu.address.logic.commands.cart.CartAddIngredientCommand;
import seedu.address.logic.commands.cart.CartAddRecipeIngredientCommand;
import seedu.address.logic.commands.recipe.RecipeAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;


public class CartAddCommandParser implements Parser<CartAddCommand> {

    public static final String RECIPE_STRING = "recipe";

    /**
     * Parses the given {@code String} of arguments in the context of the CartAddCommand
     * and returns a CartAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CartAddCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (containsRecipe(args)) {
            return parseAddRecipeIngredient(args);
        } else if (containsIngredient(args)) {
            return parseAddIngredient(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecipeAddCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the CartAddIngredientCommand
     * and returns a CartAddIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CartAddIngredientCommand parseAddIngredient(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_INGREDIENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CartAddCommand.MESSAGE_USAGE));
        }

        IngredientName ingredientName = ParserUtil.parseIngredientName(argMultimap
                .getValue(PREFIX_INGREDIENT_NAME).get());
        IngredientQuantity ingredientQuantity = ParserUtil.parseIngredientQuantity(argMultimap
                .getValue(PREFIX_INGREDIENT_QUANTITY).get());

        Ingredient ingredient = new Ingredient(ingredientName, ingredientQuantity);

        return new CartAddIngredientCommand(ingredient);

    }

    /**
     * Parses the given {@code String} of arguments in the context of the CartAddRecipeIngredientCommand
     * and returns a CartAddRecipeIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CartAddRecipeIngredientCommand parseAddRecipeIngredient(String args) throws
            NumberFormatException, ParseException {

        int recipeNumber;

        if (!hasOnlyRecipePrefixAndIndex(args)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CartAddCommand.MESSAGE_USAGE));
        }

        try {
            args = removeRecipePrefix(args);
            recipeNumber = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CartAddCommand.MESSAGE_USAGE));
        }

        return new CartAddRecipeIngredientCommand(recipeNumber);
    }

    private String removeRecipePrefix(String args) {
        return args.split(" ",2)[1];
    }

    private boolean containsRecipe(String args) {
        return args.contains(RECIPE_STRING);
    }

    private boolean containsIngredient(String args) {
        return args.contains(PREFIX_INGREDIENT_NAME.toString())
                && args.contains(PREFIX_INGREDIENT_QUANTITY.toString());
    }

    private static boolean hasOnlyRecipePrefixAndIndex(String args) {
        String trim = args.trim();
        return trim.split("\\s+").length == 2 && args.split(" ",2)[0].toLowerCase().equals("recipe");
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
