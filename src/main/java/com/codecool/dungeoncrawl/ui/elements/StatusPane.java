package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.List;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private GridPane ui;
    private Label healthTextLabel;
    private Label healthValueLabel;

    private Label inventoryTextLabel;
    private Label inventoryValueLabel;
    private int inventoryYPosition;

    public StatusPane() {
        ui = new GridPane();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        inventoryTextLabel = new Label("Inventory: ");
        inventoryValueLabel = new Label();
        inventoryYPosition = 5;
    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));

        ui.add(healthTextLabel, 0, 0);
        ui.add(healthValueLabel, 1, 0);
        ui.add(inventoryTextLabel, 0, 5);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setHealthValue(String text) {
        healthValueLabel.setText(text);
    }

    public void setInventoryValue(List<String> descriptions) {
        int numberOfLabels = 0;
        for (String description : descriptions) {
            numberOfLabels ++;
            addItemValueLabel(description, numberOfLabels);
            inventoryValueLabel.setText(description);
        }
    }

    public void addItemValueLabel(String description, int numberOfLabels) {
        Label itemLabel = new Label(description);
        ui.add(itemLabel, 0, (inventoryYPosition + numberOfLabels * 5));
    }
}
