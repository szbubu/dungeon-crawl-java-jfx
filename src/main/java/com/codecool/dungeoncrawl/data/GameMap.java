package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    public List<Actor> getAllTheEnemies(){
        List<Actor> allTheActors=new ArrayList<>();
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y <height; y++) {
                allTheActors.add(this.getCell(x,y).getActor());
            }
        }
        allTheActors.removeIf(Objects::isNull);
        allTheActors.removeIf(actor -> actor instanceof Player);

        return allTheActors;
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
