package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.LinkedList;

public class Player extends Actor {
    private final List<Item> inventory = new LinkedList<Item>();
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public List<Item> getInventory() {
        return new LinkedList<Item>(this.inventory);
    }
}
