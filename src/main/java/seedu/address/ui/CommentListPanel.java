package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Comment;



/**
 * Panel containing the list of persons.
 */
public class CommentListPanel extends UiPart<Region> {
    private static final String FXML = "CommentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CommentListPanel.class);

    @FXML
    private ListView<Comment> commentListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public CommentListPanel(ObservableList<Comment> commentList) {
        super(FXML);
        commentListView.setItems(commentList);
        commentListView.setCellFactory(listView -> new CommentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class CommentListViewCell extends ListCell<Comment> {
        @Override
        protected void updateItem(Comment comment, boolean empty) {
            super.updateItem(comment, empty);

            if (empty || comment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CommentCard(comment, getIndex() + 1).getRoot());
            }
        }
    }
}
