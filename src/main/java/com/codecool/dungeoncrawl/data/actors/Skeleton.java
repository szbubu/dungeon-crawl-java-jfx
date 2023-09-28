package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Enemy {

    public Skeleton(Cell cell) {
        super(cell, ActorStats.SKELETON.getHealth(), ActorStats.SKELETON.getDamage(), ActorStats.SKELETON.getVision());
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }


}
