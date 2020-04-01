package seedu.address.logic.commands.cookbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.recipe.RecipeNameContainsKeywordsPredicate;

public class CookbookSearchByKeywordCommandTest {
    private static final List<String> VALID_KEYWORDS_ONE = Arrays.asList("key", "words");
    private static final List<String> VALID_KEYWORDS_TWO = Collections.singletonList("keywords");
    private static final RecipeNameContainsKeywordsPredicate VALID_PREDICATE_ONE =
        new RecipeNameContainsKeywordsPredicate(VALID_KEYWORDS_ONE);
    private static final RecipeNameContainsKeywordsPredicate VALID_PREDICATE_TWO =
        new RecipeNameContainsKeywordsPredicate(VALID_KEYWORDS_TWO);
    @Test
    public void execute_validInput() {

    }

    @Test
    public void execute_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            new CookbookSearchByKeywordCommand(VALID_PREDICATE_ONE).execute(null));
    }

    @Test
    public void equalsMethod() {
        CookbookSearchByKeywordCommand c = new CookbookSearchByKeywordCommand(VALID_PREDICATE_ONE);
        assertEquals(c, c);
        assertNotEquals(c, new CookbookSearchByKeywordCommand(VALID_PREDICATE_TWO));
        assertNotEquals(c, null);
    }
}
