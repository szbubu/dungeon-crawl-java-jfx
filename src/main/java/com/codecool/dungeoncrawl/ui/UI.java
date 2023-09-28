package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class UI {
    private Canvas canvas;
    private GraphicsContext context;

    private MainStage mainStage;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;


    public UI(GameLogic logic, Set<KeyHandler> keyHandlers) {
        this.canvas = new Canvas(
                logic.getMapWidth() * Tiles.TILE_WIDTH,
                logic.getMapHeight() * Tiles.TILE_WIDTH);
        this.logic = logic;
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.keyHandlers = keyHandlers;
    }

    public void setUpPain(Stage primaryStage) {
        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);
        logic.setup();
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        try {
            for (KeyHandler keyHandler : keyHandlers) {
                keyHandler.perform(keyEvent, logic.getMap());
            }

            logic.checkIfActorsAreDead();
            logic.moveTheEnemies();
            this.refresh();
            logic.checkIfActorsAreDead();

        } catch (NullPointerException e) {
            System.out.println("GAME OVER");

        }
        refresh();
    }


    public void refresh() {
        try {
            context.setFill(Color.BLACK);
            context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            for (int x = 0; x < logic.getMapWidth(); x++) {
                for (int y = 0; y < logic.getMapHeight(); y++) {
                    Cell cell = logic.getCell(x, y);
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), x, y);
                    } else if (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), x, y);
                    } else {
                        Tiles.drawTile(context, cell, x, y);
                    }
                }
            }
            mainStage.setHealthLabelText(logic.getPlayerHealth());
            mainStage.setInventoryText(getInventoryDescription());
            mainStage.setDamageLabelText(logic.getPlayerDamage());
        } catch (NullPointerException e) {
            System.out.println("Game Over");
        }
    }

    private List<String> getInventoryDescription() {
        Player player = logic.getMap().getPlayer();
        List<Item> inventory = player.getInventory();

        if (inventory.isEmpty()) {
            return List.of();
        } else {
            List<String> descriptions = new LinkedList<>();
            for (Item item : inventory) {
                descriptions.add(item.getTileName());
            }
            return descriptions;
        }
    }
}
