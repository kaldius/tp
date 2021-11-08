package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandEnum;

/**
 * Controller for the Command Summary page.
 */
public class CommandSummaryWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(CommandSummaryWindow.class);
    private static final String FXML = "CommandSummaryWindow.fxml";

    @FXML
    private Accordion commandDescription;

    /**
     * Creates a new CommandSummaryWindow
     *
     * @param root Stage to use as the root of the CommandSummaryWindow.
     */
    //@@author jacobnbh - reused
    // code snippet adapted from fellow tutorial group, T11-2's source code with minor modifications that are
    // customized to our project
    public CommandSummaryWindow(Stage root) {
        super(FXML, root);
        for (CommandEnum command : CommandEnum.values()) {
            commandDescription.getPanes().add(new CommandDescCard(command, this).getRoot());
        }
    }
    //@@author

    public CommandSummaryWindow() {
        this(new Stage());
    }

    /**
     * Shows the command summary window.
     */
    public void show() {
        logger.fine("Showing command summary page");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the command summary window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the command summary window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the command summary window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
