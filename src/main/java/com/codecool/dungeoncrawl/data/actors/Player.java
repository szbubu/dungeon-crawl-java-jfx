package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Weapon;

import java.util.LinkedList;
import java.util.List;

public class Player extends Actor {
    private int damage = 5;
    private final List<Item> inventory = new LinkedList<Item>();
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public int getDamage(){
        int damageModifier = 0;
        for (Item item:this.inventory) {
            if (item instanceof Weapon){
                damageModifier=((Weapon) item).getPlusDamage();
            }
        }
        return this.damage+damageModifier;
    }

    public List<Item> getInventory() {
        return new LinkedList<Item>(this.inventory);
    }
}
