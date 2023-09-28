package com.codecool.dungeoncrawl.data.actors;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.data.items.Weapon;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Key>getKeys(){
        return this.inventory.stream().filter(e->e instanceof Key).map(e->(Key)e).collect(Collectors.toList());
    }

    public boolean removeFromInventor(Item itemToRemove){
        return this.inventory.remove(itemToRemove);
    }
}
