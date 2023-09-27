package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;

import java.util.List;
import java.util.Random;

public class BasicEnemyMovementHandlerImpl implements EnemyMovementHandler {
    static private final Random random = new Random();

    @Override
    public void performEnemyMovement(GameMap map) {
        List<Actor> allTheEnemies = map.getAllTheEnemies();
        for (Actor enemy:allTheEnemies) {
            moveActor(enemy);
        }
    }

    @Override
    public void performNPCMovement(GameMap map) {
        List<Actor> allTheNPCs = map.getAllNPCs();
        for (Actor npc:allTheNPCs) {
            moveActor(npc);
        }
    }

    private void moveActor(Actor actor) {
        int[] coordinates = {0, 0};
        int[] possibleDirections = {-1, 1};
        int randomAxis = random.nextInt(2);
        int randomDirection = possibleDirections[random.nextInt(2)];
        coordinates[randomAxis] = randomDirection;
        actor.move(coordinates[0], coordinates[1]);
    }
}
