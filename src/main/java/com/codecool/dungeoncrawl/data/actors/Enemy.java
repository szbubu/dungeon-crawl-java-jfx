package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class  Enemy extends Actor{
    private int vision;
    public Enemy(Cell cell) {
        super(cell);
    }

    public int getVision() {
        return vision;
    }
}
