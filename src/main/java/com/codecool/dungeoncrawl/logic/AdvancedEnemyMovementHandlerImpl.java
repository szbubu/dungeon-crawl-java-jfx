package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Enemy;
import com.codecool.dungeoncrawl.data.actors.Player;

import java.util.List;
import java.util.Random;

public class AdvancedEnemyMovementHandlerImpl implements EnemyMovementHandler{
    private static final Random random = new Random();

    @Override
    public void performEnemyMovement(GameMap map) {
        Player player = map.getPlayer();
        List<Actor> allTheEnemies = map.getAllTheEnemies();
        for (Actor enemy:allTheEnemies) {
            if(checkIfEnemySeesPlayer(player,(Enemy)enemy)){
                moveEnemySmartly(player, enemy, map);
            }
            else{
                moveActorRandomly(enemy);
            }
        }
    }
    @Override
    public void performNPCMovement(GameMap map) {
            List<Actor> allTheNPCs = map.getAllNPCs();
            for (Actor npc:allTheNPCs) {
                moveActorRandomly(npc);
        }
    }

    private void moveActorRandomly(Actor enemy) {
        int[] coordinates = {0, 0};
        int[] possibleDirections = {-1, 1};
        int randomAxis = random.nextInt(2);
        int randomDirection = possibleDirections[random.nextInt(2)];
        coordinates[randomAxis] = randomDirection;
        enemy.move(coordinates[0], coordinates[1]);
    }
    private boolean checkIfEnemySeesPlayer(Player player, Enemy enemy){
        return Math.abs(player.getX() - enemy.getX()) < enemy.getVision() || Math.abs(player.getY() - enemy.getY()) < enemy.getVision();
    }
    private void moveEnemySmartly(Player player, Actor enemy, GameMap map){
        int movementOnX = whereToMoveOnAxisX(player,enemy,map);
        int movementOnY = whereToMoveOnAxisY(player,enemy,map);
        if(movementOnX==0 && movementOnY==0){
            moveActorRandomly(enemy);
        }
        else if (movementOnX!=0 && movementOnY!=0){
            int[][] possibleMovements = {{0,movementOnY}, {movementOnX,0}};
            int [] randomMovementCoordinates = possibleMovements[random.nextInt(possibleMovements.length)];
            enemy.move(randomMovementCoordinates[0], randomMovementCoordinates[1]);
        }else if (movementOnX==0) {
            enemy.move(0, movementOnY);
        }
        else{
            enemy.move(movementOnX,0);
        }
    }
    private int whereToMoveOnAxisX(Player player, Actor enemy, GameMap map){
        if(player.getX()< enemy.getX()&& map.getCell(enemy.getX()-1,enemy.getY()).getType().isPassable()){
            return -1;
        }
        if (player.getX()> enemy.getX()&& map.getCell(enemy.getX()+1, enemy.getY()).getType().isPassable()){
            return 1;
        }
        return 0;
    }
    private int whereToMoveOnAxisY(Player player, Actor enemy, GameMap map){
        if(player.getY()< enemy.getY()&& map.getCell(enemy.getX(),enemy.getY()-1).getType().isPassable()){
            return -1;
        }
        if (player.getY()> enemy.getY()&& map.getCell(enemy.getX(), enemy.getY()+1).getType().isPassable()){
            return 1;
        }
        return 0;
    }

}
