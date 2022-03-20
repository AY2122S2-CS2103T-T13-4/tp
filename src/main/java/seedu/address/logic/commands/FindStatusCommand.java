package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;



/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindStatusCommand extends Command {

    public static final String COMMAND_WORD = "findstatus";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private static final String MESSAGE_SUCCESS = "Found status for %s:";

    private final Index index;

    public FindStatusCommand(Index index) {
        this.index = index;;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToFindStatus = lastShownList.get(index.getZeroBased());
        Status statusToBeFound = personToFindStatus.getStatus();

        model.updateFilteredStatusList(x -> x.equals(statusToBeFound));
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, personToFindStatus.getName().fullName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindStatusCommand // instanceof handles nulls
                && index.equals(((FindStatusCommand) other).index)); // state check
    }
}
