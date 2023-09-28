package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Door;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.items.Key;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Actor implements Drawable {
    protected Cell cell;
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

    private void attack(Actor actor) {
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
        }
    }

    public int getHealth() {
        return health;
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
