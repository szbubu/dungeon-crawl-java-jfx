package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;

public interface EnemyMovementHandler {
    public void performEnemyMovement(GameMap map);
    public void performNPCMovement(GameMap map);
}
