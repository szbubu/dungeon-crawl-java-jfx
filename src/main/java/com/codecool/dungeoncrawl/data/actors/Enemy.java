package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class  Enemy extends Actor{
    protected int vision;
    public Enemy(Cell cell, int health, int damage, int vision) {
        super(cell, health, damage);
        this.vision=vision;
    }

    public int getVision() {
        return vision;
    }
}
