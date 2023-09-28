package com.codecool.dungeoncrawl.ui.elements;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class MainStage {
    private Canvas canvas;
    private Scene scene;
    private StatusPane statusPane;

    public MainStage(Canvas canvas) {
        this.canvas = canvas;
        statusPane = new StatusPane();
        scene = setUpScene();
    }

    private Scene setUpScene() {
        BorderPane borderPane = statusPane.build();
        borderPane.setCenter(canvas);
        Scene scene = new Scene(borderPane);
        return scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setHealthLabelText(String text) {
        this.statusPane.setHealthValue(text);

    }
    public void setDamageLabelText(String damageValue){
        this.statusPane.setDamageValueLabel(damageValue);
    }
    public void setInventoryText(List<String> descriptions) {
        this.statusPane.setInventoryValue(descriptions);
    }
    public void setCurrentWeaponLabel(String currentWeapon) {
        this.statusPane.setCurrentWeaponValue(currentWeapon);
    }
}
