package seedu.address.model.recipe;

import java.util.Iterator;

/**
 * A list of recipes that enforces uniqueness between its elements and does not allow nulls.
 * A recipe is considered unique by comparing using {@code Recipe#isSameRecipe(Recipe)}. As such, adding and updating of
 * recipes uses Recipe#isSameRecipe(Recipe) for equality so as to ensure that the recipe being added or updated is
 * unique in terms of identity in the UniqueRecipeList. However, the removal of a recipe uses Recipe#equals(Object) so
 * as to ensure that the recipe with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Recipe#isSameRecipe(Recipe)
 */
public class UniqueRecipeList implements Iterable<Recipe> {
    @Override
    public Iterator<Recipe> iterator() {
        return null;
    }
}
