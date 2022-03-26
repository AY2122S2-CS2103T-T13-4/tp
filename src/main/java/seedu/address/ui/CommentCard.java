package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Comment;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class CommentCard extends UiPart<Region> {

    private static final String FXML = "CommentListCard.fxml";
    public final Comment Comment;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public CommentCard(Comment Comment, int displayedIndex) {
        super(FXML);
        this.Comment = Comment;
        id.setText(displayedIndex + ". ");
        name.setText(Comment.toString());
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommentCard)) {
            return false;
        }

        // state check
        CommentCard card = (CommentCard) other;
        return id.getText().equals(card.id.getText());
    }
}