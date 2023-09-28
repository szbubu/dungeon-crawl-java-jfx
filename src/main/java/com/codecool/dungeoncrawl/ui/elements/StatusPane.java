package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private final GridPane ui;
    private final Label healthTextLabel;
    private final Label healthValueLabel;
    private final Label currentWeaponTextLabel;
    private final Label currentWeaponValueLabel;
    private final Label damageValueLabel;
    private final Label damageTextLabel;
    private final Label inventoryTextLabel;
    private final int inventoryYPosition;
    private final List<String> inventoryItems;
    private final Label statusLabel;

    public StatusPane() {
        ui = new GridPane();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        currentWeaponTextLabel = new Label("Selected weapon: ");
        currentWeaponValueLabel = new Label();
        damageTextLabel = new Label("Player Damage: ");
        damageValueLabel = new Label();
        inventoryTextLabel = new Label("Inventory: ");
        inventoryYPosition = 15;
        inventoryItems = new LinkedList<>();
        statusLabel = new Label();
    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));

        ui.add(healthTextLabel, 0, 0);
        ui.add(healthValueLabel, 1, 0);
        ui.add(currentWeaponTextLabel, 0, 5);
        ui.add(currentWeaponValueLabel, 1, 5);
        ui.add(damageTextLabel, 0, 10);
        ui.add(damageValueLabel, 1, 10);
        ui.add(inventoryTextLabel, 0, inventoryYPosition);
        ui.add(statusLabel, 0, 50);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setHealthValue(String text) {
        healthValueLabel.setText(text);
    }

    public void setDamageValueLabel(String damageValue) {
        this.damageValueLabel.setText(damageValue);
    }

    public void setInventoryValue(List<String> descriptions) {
        if (descriptions.size() > inventoryItems.size()) {
            addItemLabels(descriptions);
        } else if (descriptions.size() < inventoryItems.size()) {
            removeLabels(ui, "itemLabel");
            this.inventoryItems.clear();
            addItemLabels(descriptions);
        }
    }

    private void addItemLabels(List<String> descriptions) {
        for (String description : descriptions) {
            if (!(inventoryItems.contains(description))) {
                addItemValueLabel(description);
            }
        }
    }

    public void addItemValueLabel(String description) {
        Label itemLabel = new Label(description);
        itemLabel.setUserData("itemLabel");
        inventoryItems.add(description);
        int distanceBetweenLabels = 5;
        ui.add(itemLabel, 0, (inventoryYPosition + inventoryItems.size() * distanceBetweenLabels));
    }

    private void removeLabels(Pane parentPane, String target) {
        parentPane.getChildren()
                .removeIf(node -> {
                    if (node instanceof Label) {
                        return node.getUserData() != null && node.getUserData().equals(target);
                    }
                    return false;
                });
    }

    public void setCurrentWeaponValue(String currentWeapon) {
        currentWeaponValueLabel.setText(currentWeapon);
    }

    public void setStatusLabel(String status) {
        statusLabel.setText(status);
    }
}
