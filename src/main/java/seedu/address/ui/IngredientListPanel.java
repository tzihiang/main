package seedu.address.ui;

// import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
// import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class IngredientListPanel extends UiPart<Region> {
    private static final String FXML = "CartIngredientListPanel.fxml";
    // private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Ingredient> cartIngredientListView;

    public IngredientListPanel(ObservableList<Ingredient> ingredientList) {
        super(FXML);
        cartIngredientListView.setItems(ingredientList);
        cartIngredientListView.setCellFactory(listView -> new CartIngredientListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class CartIngredientListViewCell extends ListCell<Ingredient> {
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
