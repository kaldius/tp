package seedu.address.model.tag;

import javafx.scene.control.Label;

/**
 * Represents a Pseudo-class for a tag with the label "URGENT"
 */
public class UrgentLabel extends Label {

    /**
     * Constructor for an {@code URGENT} label.
     */
    public UrgentLabel() {
        super("URGENT");
        getStyleClass().add("urgent-label");
    }
}
