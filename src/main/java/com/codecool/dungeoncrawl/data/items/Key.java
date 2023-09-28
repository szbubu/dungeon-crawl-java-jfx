package com.codecool.dungeoncrawl.data.items;
import com.codecool.dungeoncrawl.data.Cell;

public class Key extends Item{
    private final int doorID;

    public Key(Cell cell, String name, int doorId) {
        super(cell, name);
        this.doorID=doorId;
    }

    public String getTileName() {
        return "key";
    }
}
