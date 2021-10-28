package seedu.address.model.tag;

import javafx.scene.control.Label;

/**
 * Represents a Pseudo-class for a tag with the label "supplementary"
 */
public class SupplementaryLabel extends Label {

    /**
     * Constructor for an {@code supplementary} label.
     */
    public SupplementaryLabel() {
        super("supplementary");
        getStyleClass().add("supplementary-label");
    }
}
