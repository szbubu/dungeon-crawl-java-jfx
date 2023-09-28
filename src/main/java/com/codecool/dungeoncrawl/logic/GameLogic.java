package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;

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

    public String getPlayerDamage(){
        return Integer.toString(map.getPlayer().getDamage());
    }

    public GameMap getMap() {
        return map;
    }
    public void checkIfActorsAreDead(){
        for(Actor actor : map.getAllActors()) {
            if (!isAlive(actor)) {
                actor.getCell().setActor(null);
                if(actor instanceof Player) {
                    map.setPlayer(null);
                }
            }
        }
    }
    public boolean isAlive(Actor actor){
        return actor.getHealth() > 0;
    }
    public void moveTheEnemies() {
        enemyMovementHandler.performEnemyMovement(this.map);
    }
}


