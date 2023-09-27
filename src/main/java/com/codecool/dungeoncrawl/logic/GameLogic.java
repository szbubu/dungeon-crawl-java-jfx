package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class GameLogic {
    private GameMap map;
    private final EnemyMovementHandler enemyMovementHandler;

    public GameLogic(EnemyMovementHandler enemyMovementHandler) {
        this.map = MapLoader.loadMap();
        this.enemyMovementHandler = enemyMovementHandler;
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }

    public GameMap getMap() {
        return map;
    }

    public void moveTheEnemies() {
        enemyMovementHandler.performEnemyMovement(this.map);
    }
}


