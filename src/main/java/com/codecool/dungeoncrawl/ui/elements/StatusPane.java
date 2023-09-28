package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javax.swing.text.LabelView;
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
    private Label currentWeaponTextLabel;
    private Label getCurrentWeaponValueLabel;
    private Label damageValueLabel;
    private Label damageTextLabel;
    private int inventoryYPosition;
    private List<String> inventoryItems;

    public StatusPane() {
        ui = new GridPane();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        currentWeaponTextLabel = new Label("Selected weapon: ");
        getCurrentWeaponValueLabel = new Label();
        damageTextLabel = new Label("Player Damage: ");
        damageValueLabel = new Label();
        inventoryTextLabel = new Label("Inventory: ");
        inventoryValueLabel = new Label();
        inventoryYPosition = 15;
        inventoryItems = new LinkedList<>();
    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));

        ui.add(healthTextLabel, 0, 0);
        ui.add(healthValueLabel, 1, 0);
        ui.add(currentWeaponTextLabel, 0, 5);
        ui.add(getCurrentWeaponValueLabel, 1, 5);
        ui.add(damageTextLabel, 0, 10);
        ui.add(damageValueLabel, 1, 10);
        ui.add(inventoryTextLabel, 0, inventoryYPosition);

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
        getCurrentWeaponValueLabel.setText(currentWeapon);
    }
}
