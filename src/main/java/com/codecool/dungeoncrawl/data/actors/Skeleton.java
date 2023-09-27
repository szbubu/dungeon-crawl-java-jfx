package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Enemy {
    public Skeleton(Cell cell) {
        super(cell, 10, 2);
        this.vision = 4;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }


}
