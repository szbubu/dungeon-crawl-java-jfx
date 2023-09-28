package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private final GameMap gameMap;
    private final int x, y;

    private Item item;

    public Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;

    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Actor getActor() {
        return actor;
    }

    public Item getItem() {
        return item;
    }

    public Cell getNeighbor(int dx, int dy) {
        if (this.x + dx >= gameMap.getWidth() || this.y + dy >= gameMap.getHeight()) {
            return null;
        }
        if (this.x + dx < 0 || this.y + dy < 0) {
            return null;
        }
        return gameMap.getCell(x + dx, y + dy);
    }

    public List<Cell> getAllNeighbors() {
        List<Cell> neighboringCells = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y < 1; y++) {
                neighboringCells.add(getNeighbor(x, y));
            }
        }
        neighboringCells.remove(this);
        return neighboringCells;
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
