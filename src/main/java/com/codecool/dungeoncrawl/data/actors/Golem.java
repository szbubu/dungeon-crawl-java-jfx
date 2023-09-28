package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Golem extends Enemy {

    public Golem(Cell cell) {
        super(cell, 15, 3);
        this.vision = 1;
    }
    @Override
    public String getTileName() {
        return "golem";
    }
}
