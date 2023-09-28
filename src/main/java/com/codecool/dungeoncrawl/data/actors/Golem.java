package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Golem extends Enemy {

    public Golem(Cell cell) {
        super(cell, ActorStats.GOLEM.getHealth(), ActorStats.GOLEM.getDamage(), ActorStats.GOLEM.getVision());
    }
    @Override
    public String getTileName() {
        return "golem";
    }
}
