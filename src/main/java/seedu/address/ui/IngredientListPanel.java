package seedu.address.ui;

// import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
// import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ingredient.Ingredient;

/**
 * Panel containing the list of ingredients.
 */
public class IngredientListPanel extends UiPart<Region> {
    private static final String FXML = "IngredientListPanel.fxml";
    // private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Ingredient> ingredientListView;

    public IngredientListPanel(ObservableList<Ingredient> ingredientList) {
        super(FXML);
        ingredientListView.setItems(ingredientList);
        ingredientListView.setCellFactory(listView -> new IngredientListViewCell());
    }

    /**
     * Custom {@code ListCell} displays the graphics of a {@code UniqueIngredientList} using {@code IngredientCard}.
     */
    class IngredientListViewCell extends ListCell<Ingredient> {
        @Override
        protected void updateItem(Ingredient ingredient, boolean empty) {
            super.updateItem(ingredient, empty);

            if (empty || ingredient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new IngredientCard(ingredient, getIndex() + 1).getRoot());
            }
        }
    }

}
