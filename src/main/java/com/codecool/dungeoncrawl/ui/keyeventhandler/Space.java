package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Space implements KeyHandler {
    public static final KeyCode code = KeyCode.SPACE;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if (code.equals(event.getCode())) {
            Player player = map.getPlayer();
            Cell cell = player.getCell();
            Item item = cell.getItem();
            if (item != null) {
                player.addToInventory(item);
                cell.setItem(null);
            }
        }
    }
}