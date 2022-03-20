package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.SortCommand.PersonComparator;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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
public class UniqueStatusList implements Iterable<Status> {

    private final ObservableList<Status> internalList = FXCollections.observableArrayList();
    private final ObservableList<Status> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent status as the given argument.
     */
    public boolean contains(Status toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a status to the list.
     * The status must not already exist in the list.
     */
    public void add(Status toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setStatus(Status target, Status editedStatus) {
        requireAllNonNull(target, editedStatus);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.equals(editedStatus) && contains(editedStatus)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedStatus);
    }

//    /**
//     * Sort the list using the given comparator.
//     */
//    public void sort(PersonComparator comparator) {
//        internalList.sort(comparator);
//    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Status toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setStatuses(UniqueStatusList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setStatuses(List<Status> statuses) {
        requireAllNonNull(statuses);
        if (!statusesAreUnique(statuses)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(statuses);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Status> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Status> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStatusList // instanceof handles nulls
                        && internalList.equals(((UniqueStatusList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean statusesAreUnique(List<Status> statuses) {
        for (int i = 0; i < statuses.size() - 1; i++) {
            for (int j = i + 1; j < statuses.size(); j++) {
                if (statuses.get(i).equals(statuses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
