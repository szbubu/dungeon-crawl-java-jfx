package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Enemy;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.codecool.dungeoncrawl.data.items.Item;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }
    public void setCell(Cell cell){
        int x = cell.getX();
        int y = cell.getY();
        this.cells[x][y] = cell;
    }


    public List<Actor> getAllTheEnemies(){
        List<Actor> allTheEnemies=new ArrayList<>();
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y <height; y++) {
                Actor actor = this.getCell(x,y).getActor();
                if(actor instanceof Enemy){
                    allTheEnemies.add(actor);
                }
            }
        }
        return allTheEnemies;
    }

    public List<Actor> getAllActors(){
        List<Actor> allTheActors=new ArrayList<>();
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y <height; y++) {
                Actor actor = this.getCell(x,y).getActor();
                if(actor != null){
                    allTheActors.add(actor);
                }
            }
        }
        return allTheActors;
    }
    public List<Actor> getAllNPCs(){
        List<Actor> allTheNPCs = new ArrayList<>();
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y <height; y++) {
                Actor actor = this.getCell(x,y).getActor();
                if(actor != null && !(actor instanceof Player) &&!(actor instanceof Enemy)){
                    allTheNPCs.add(actor);
                }
            }
        }
        return allTheNPCs;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
