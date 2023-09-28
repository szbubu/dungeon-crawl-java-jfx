package com.codecool.dungeoncrawl.data.items;
import com.codecool.dungeoncrawl.data.Cell;

public class Key extends Item{
    private final int id;

    public Key(Cell cell, String name, int doorId) {
        super(cell, name);
        this.id =doorId;
    }

    public int getId() {
        return id;
    }

    public String getTileName() {
        return "key";
    }
}
