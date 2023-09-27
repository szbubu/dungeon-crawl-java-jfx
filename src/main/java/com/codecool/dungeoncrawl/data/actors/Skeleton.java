package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Enemy {
    private final int vision;
    public Skeleton(Cell cell) {
        super(cell);
        this.vision = 4;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }


}
