package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.LinkedList;
import java.util.List;

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
