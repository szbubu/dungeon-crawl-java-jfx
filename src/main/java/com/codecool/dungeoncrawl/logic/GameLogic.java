package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Weapon;

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
        Player player = map.getPlayer();

        if (player == null) {
            return "0";
        } else {
            int health = player.getHealth();
            if (health <= 0) return "0";
            return Integer.toString(map.getPlayer().getHealth());
        }
    }

    public String getPlayerDamage(){
        if (map.getPlayer() == null) {
            return "0";
        }
        return Integer.toString(map.getPlayer().getDamage());
    }

    public String getPlayerCurrentWeapon() {
        Weapon currentWeapon = map.getPlayer().getCurrentWeapon();
        if (currentWeapon == null) {
            return "None";
        }
        return currentWeapon.getTileName();
    }

    public GameMap getMap() {
        return map;
    }

    public void removeDeadActors() {
        for (Actor actor : map.getAllActors()) {
            if (!(actor.isAlive())) {
                actor.getCell().setActor(null);
                if (actor instanceof Player) {
                    map.setPlayer(null);
                }
            }
        }
    }

    public boolean hasPlayerLost() {
        return map.getPlayer() == null;
    }

    public boolean hasPlayerWon() {
        return map.getAllTheEnemies().isEmpty();
    }

    public boolean isGameOver() {
        return hasPlayerLost() || hasPlayerWon();
    }

    public void moveTheEnemies() {
        enemyMovementHandler.performEnemyMovement(this.map);
    }

    public String getStatus() {
        if (hasPlayerWon()) {
            return "You won!";
        } else if (hasPlayerLost()) {
            return "You lost!";
        } else {
            return "";
        }
    }
}


