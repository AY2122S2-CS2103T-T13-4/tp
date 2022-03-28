package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueCommentList implements Iterable<Comment> {
    private static final int CAPACITY = 10;
    private final LruCache<Comment> commentCache = new LruCache<>(CAPACITY);
    private final ObservableList<Comment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Comment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Comment as the given argument.
     */
    public boolean contains(Comment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a Comment to the list.
     * The Comment must not already exist in the list.
     */
    public void addToCache(Comment toAdd) {
        requireNonNull(toAdd);
        commentCache.put(toAdd);
        ObservableList<Comment> list = commentCache.getList();
        internalList.clear();
        internalList.addAll(list);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setComments(List<Comment> comments) {
        requireAllNonNull(comments);

        for (Comment comment : comments) {
            addToCache(comment);
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Comment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Comment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCommentList // instanceof handles nulls
                && internalList.equals(((UniqueCommentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
