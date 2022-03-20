package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STATUS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListStatusCommand extends Command {

    public static final String COMMAND_WORD = "liststatus";

    public static final String MESSAGE_SUCCESS = "Listed all statuses";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStatusList(PREDICATE_SHOW_ALL_STATUS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
