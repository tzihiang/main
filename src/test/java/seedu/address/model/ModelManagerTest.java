package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.CARBONARA;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Cookbook(), new Cookbook(modelManager.getCookbook()));
        assertEquals(new Inventory(), new Inventory(modelManager.getInventory()));
        assertEquals(new Cart(), new Cart(modelManager.getCart()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setCookbookFilePath(Paths.get("cookbook/file/path"));
        userPrefs.setInventoryFilePath(Paths.get("inventory/file/path"));
        userPrefs.setCartFilePath(Paths.get("cart/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setCookbookFilePath(Paths.get("new/cookbook/file/path"));
        userPrefs.setInventoryFilePath(Paths.get("new/inventory/file/path"));
        userPrefs.setCartFilePath(Paths.get("new/cart/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setCookbookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setCookbookFilePath(null));
    }

    @Test
    public void setCookbookFilePath_validPath_setsCookbookFilePath() {
        Path path = Paths.get("cookbook/file/path");
        modelManager.setCookbookFilePath(path);
        assertEquals(path, modelManager.getCookbookFilePath());
    }

    @Test
    public void setInventoryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setInventoryFilePath(null));
    }

    @Test
    public void setInventoryFilePath_validPath_setsInventoryFilePath() {
        Path path = Paths.get("inventory/file/path");
        modelManager.setInventoryFilePath(path);
        assertEquals(path, modelManager.getInventoryFilePath());
    }

    @Test
    public void setCartFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setCartFilePath(null));
    }

    @Test
    public void setCartFilePath_validPath_setsCartFilePath() {
        Path path = Paths.get("cart/file/path");
        modelManager.setCartFilePath(path);
        assertEquals(path, modelManager.getCartFilePath());
    }

    @Test
    public void hasCookbookRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCookbookRecipe(null));
    }

    @Test
    public void hasCookbookRecipe_recipeNotInCookbook_returnsFalse() {
        assertFalse(modelManager.hasCookbookRecipe(CARBONARA));
    }

    @Test
    public void hasCookbookRecipe_recipeInCookbook_returnsTrue() {
        modelManager.addCookbookRecipe(CARBONARA);
        assertTrue(modelManager.hasCookbookRecipe(CARBONARA));
    }

    @Test
    public void getFilteredCookbookRecipeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCookbookRecipeList().remove(0));
    }

    @Test
    public void equals() {
    }
}
