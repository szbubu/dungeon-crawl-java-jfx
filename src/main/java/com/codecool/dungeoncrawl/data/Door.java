package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.items.Key;

public class Door extends Cell{
    private final int id;
    public Door(GameMap gameMap, int x, int y, CellType type, int id) {
        super(gameMap, x, y, type);
        this.id=id;
    }

    public boolean tryToOpen(Key key){
        if(key.getId()==this.id){
            this.openDoor();
            return true;
        }
        else return false;
    }
    private void openDoor(){
        this.setType(CellType.FLOOR);
        openNeighboringDoors();
    }
    private void openNeighboringDoors(){
        this.getAllNeighbors().stream().filter(c->c.getType().equals(CellType.CLOSED_DOOR)).map(d->(Door)d).forEach(Door::openDoor);
    }
}
