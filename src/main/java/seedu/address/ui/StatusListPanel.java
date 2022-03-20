package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

import java.util.logging.Logger;

/**
 * Panel containing the list of persons.
 */
public class StatusListPanel extends UiPart<Region> {
    private static final String FXML = "StatusListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StatusListPanel.class);

    @FXML
    private ListView<Status> statusListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public StatusListPanel(ObservableList<Status> statusList) {
        super(FXML);
        System.out.println("Line 29 Statuslistpanel");
        System.out.println(statusList);
        statusListView.setItems(statusList);
        statusListView.setCellFactory(listView -> new StatusListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class StatusListViewCell extends ListCell<Status> {
        @Override
        protected void updateItem(Status status, boolean empty) {
            super.updateItem(status, empty);

            if (empty || status == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StatusCard(status, getIndex() + 1).getRoot());
            }
        }
    }

}
