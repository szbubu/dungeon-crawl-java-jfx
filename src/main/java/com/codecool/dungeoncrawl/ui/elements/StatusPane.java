package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;
import java.util.List;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private final int distanceBetweenLabels = 5;
    private GridPane ui;
    private Label healthTextLabel;
    private Label healthValueLabel;

    private Label inventoryTextLabel;
    private Label inventoryValueLabel;
    private int inventoryYPosition;
    private List<String> inventoryItems;

    public StatusPane() {
        ui = new GridPane();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        inventoryTextLabel = new Label("Inventory: ");
        inventoryValueLabel = new Label();
        inventoryYPosition = distanceBetweenLabels;
        inventoryItems = new LinkedList<>();
    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));

        ui.add(healthTextLabel, 0, 0);
        ui.add(healthValueLabel, 1, 0);
        ui.add(inventoryTextLabel, 0, inventoryYPosition);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setHealthValue(String text) {
        healthValueLabel.setText(text);
    }

    public void setInventoryValue(List<String> descriptions) {
        if (descriptions.size() != inventoryItems.size()) {
            for (String description : descriptions) {
                if (!(inventoryItems.contains(description))) {
                    addItemValueLabel(description);
                }
            }
        }
    }

    public void addItemValueLabel(String description) {
        Label itemLabel = new Label(description);
        inventoryItems.add(description);
        ui.add(itemLabel, 0, (inventoryYPosition + inventoryItems.size() * distanceBetweenLabels));
    }
}
