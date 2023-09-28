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

    private final List<Item> inventory = new LinkedList<>();
    private Weapon currentWeapon;

    public Player(Cell cell) {
        super(cell, ActorStats.PLAYER.getHealth(), ActorStats.PLAYER.getDamage());
    }

    public String getTileName() {
        return "player";
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    @Override
    public int getDamage() {
        int damageModifier = currentWeapon.getDamageModifier();
        return this.damage + damageModifier;
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType().equals(CellType.CLOSED_DOOR)) {
            this.tryToOpenDoor(nextCell);
        }
        super.move(dx, dy);
    }

    private void tryToOpenDoor(Cell nextCell) {
        Key matchingKey = findMatchingKey(nextCell);
        if (matchingKey == null) {
            return;
        }
        this.inventory.remove(matchingKey);
    }

    private Key findMatchingKey(Cell nextCell) {
        Door door = (Door) nextCell;
        List<Key> keys = this.getKeys();
        List<Key> matchingKeys = keys.stream().filter(door::tryToOpen)
                .collect(Collectors.toList());
        if (matchingKeys.isEmpty()) {
            return null;
        }
        return matchingKeys.get(0);
    }

    public List<Item> getInventory() {
        return new LinkedList<>(this.inventory);
    }

    public List<Key> getKeys() {
        return this.inventory.stream().filter(e -> e instanceof Key).map(e -> (Key) e).collect(Collectors.toList());
    }

    private List<Weapon> getWeapons() {
        return inventory.stream()
                .filter(i -> i instanceof Weapon)
                .map(w -> (Weapon) w)
                .collect(Collectors.toList());
    }

    public void switchActiveWeapon() {
        List<Weapon> weapons = this.getWeapons();
        int indexOfActiveWeapon = weapons.indexOf(this.currentWeapon);
        this.currentWeapon = getNextWeapon(weapons, indexOfActiveWeapon);
    }

    private Weapon getNextWeapon(List<Weapon> weapons, int indexOfActiveWeapon) {
        int next = indexOfActiveWeapon + 1;

        if (next == weapons.size()) {
            return weapons.get(0);
        } else return weapons.get(next);
    }
}
