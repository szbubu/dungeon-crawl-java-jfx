package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Door;
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
                damageModifier = ((Weapon) item).getDamageModifier();
            }
        }
        return this.damage + damageModifier;
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType().equals(CellType.CLOSED_DOOR)) {
            Key matchingKey = this.findMatchingKey(nextCell);
            if (matchingKey == null) {
                return;
            } else if (matchingKey instanceof Key) {
                Door door = (Door) nextCell;
                door.openDoor();
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
                this.removeFromInventory(matchingKey);
            }
        } else {
            super.move(dx, dy);
        }
    }

    private Key findMatchingKey(Cell nextCell) {
        Door door = (Door) nextCell;
        Player player = (Player) this;
        List<Key> keys = player.getKeys();
        List<Key> matchingKeys = keys.stream().filter(e -> e.getId() == door.getId())
                .collect(Collectors.toList());
        if (matchingKeys.isEmpty()) {
            return null;
        }
        return matchingKeys.get(0);

    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public List<Item> getInventory() {
        return new LinkedList<Item>(this.inventory);
    }

    public List<Key> getKeys() {
        return this.inventory.stream().filter(e -> e instanceof Key).map(e -> (Key) e).collect(Collectors.toList());
    }

    public boolean removeFromInventory(Item itemToRemove) {
        return this.inventory.remove(itemToRemove);
    }
}
