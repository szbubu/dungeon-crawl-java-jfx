package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;

import java.util.List;
import java.util.Random;

public class AdvancedEnemyMovementHandlerImpl implements EnemyMovementHandler{
    static final Random random = new Random();
    @Override
    public void performEnemyMovement(GameMap map) {
        Player player = map.getPlayer();
        List<Actor> allTheEnemies = map.getAllTheEnemies();
        for (Actor enemy:allTheEnemies) {
            if(checkIfPlayerIsCloseToEnemy(player,enemy)){
                moveEnemySmartly(player,enemy, map);
            }
            else{
                moveEnemyRandomly(enemy);
            }
        }
    }
    private void moveEnemyRandomly(Actor enemy) {
        int[] coordinates = {0, 0};
        int[] possibleDirections = {-1, 1};
        int randomAxis = random.nextInt(2);
        int randomDirection = possibleDirections[random.nextInt(2)];
        coordinates[randomAxis] = randomDirection;
        enemy.move(coordinates[0], coordinates[1]);
    }
    private boolean checkIfPlayerIsCloseToEnemy(Player player, Actor enemy){
        return Math.abs(player.getX() - enemy.getX()) < 4 || Math.abs(player.getY() - enemy.getY()) < 4;
    }
    private void moveEnemySmartly(Player player, Actor enemy, GameMap map){
        int movementOnX = whereToMoveOnAxisX(player,enemy,map);
        int movementOnY = whereToMoveOnAxisY(player,enemy,map);
        if(movementOnX==0 && movementOnY==0){
            moveEnemyRandomly(enemy);
        } else if (movementOnX==0) {
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
