package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Comment;

/**
 * Jackson-friendly version of {@link Comment}.
 */
class JsonAdaptedComment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Comment's %s field is missing!";

    private final String value;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedComment(@JsonProperty("value") String value) {
        this.value = value;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedComment(Comment source) {
        this.value = source.toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Comment toModelType() throws IllegalValueException {

        if (value == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "value"));
        }
        if (!Comment.isValidComment(value)) {
            throw new IllegalValueException(Comment.MESSAGE_CONSTRAINTS);
        }

        return new Comment(value);
    }
}