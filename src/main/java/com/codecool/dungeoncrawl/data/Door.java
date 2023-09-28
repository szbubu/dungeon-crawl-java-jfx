package com.codecool.dungeoncrawl.data;

public class Door extends Cell{
    private int id;
    public Door(GameMap gameMap, int x, int y, CellType type, int id) {
        super(gameMap, x, y, type);
        this.id=id;
    }

    public int getId() {
        return id;
    }
}
