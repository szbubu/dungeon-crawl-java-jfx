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
    public void openDoor(){
        this.setType(CellType.FLOOR);
        openNeighboringDoors();
    }
    private void openNeighboringDoors(){
        this.getAllNeighbors().stream().filter(c->c.getType().equals(CellType.CLOSED_DOOR)).map(d->(Door)d).forEach(Door::openDoor);
    }
}
