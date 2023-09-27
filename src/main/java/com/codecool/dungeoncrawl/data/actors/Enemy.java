package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class  Enemy extends Actor{
    protected int vision;
    public Enemy(Cell cell, int health, int damage) {
        super(cell, health, damage);
    }

    public int getVision() {
        return vision;
    }
}
