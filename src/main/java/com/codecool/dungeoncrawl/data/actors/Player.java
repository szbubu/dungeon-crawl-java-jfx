package com.codecool.dungeoncrawl.data.actors;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Weapon;

import java.util.LinkedList;
import java.util.List;

public class Player extends Actor {


    private final List<Item> inventory = new LinkedList<Item>();

    public Player(Cell cell) {
        super(cell, 10, 5);
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public int getDamage() {
        int damageModifier = 0;
        for (Item item : this.inventory) {
            if (item instanceof Weapon) {
                damageModifier=((Weapon) item).getDamageModifier();
            }
        }
        return this.damage+damageModifier;
    }


    public void addToInventory(Item item){
        inventory.add(item);
    }
  
    public List<Item> getInventory() {
        return new LinkedList<Item>(this.inventory);
    }
}
