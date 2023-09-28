package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Door;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.items.Key;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    protected int damage;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);

    }

    public Actor(Cell cell, int health, int damage) {
        this.cell = cell;
        this.cell.setActor(this);
        this.health = health;
        this.damage = damage;
    }

    public void attack(Actor actor) {
        actor.applyDamageToHealth(this.getDamage());
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getActor() != null) {
            this.attack(nextCell.getActor());
        } else if (nextCell.getType().isPassable()) {
            if (nextCell.getActor() == null) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }
        } else if (nextCell.getType().equals(CellType.CLOSED_DOOR) && this instanceof Player) {
            Key matchingKey = this.findMatchingKey(nextCell);
            if (matchingKey == null) {
                return;
            } else if (matchingKey instanceof Key) {
                nextCell.setType(CellType.FLOOR);
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
                Player player = (Player) this;
                player.removeFromInventor(matchingKey);
            }

        }

    }

    public int getHealth() {
        return health;
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

    public void applyDamageToHealth(int damage) {
        this.health -= damage;
    }

    public int getDamage() {
        return this.damage;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

}
