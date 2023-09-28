package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Vampire extends Enemy{
    public Vampire(Cell cell) {
        super(cell, 5, 1);
        this.vision = 4;
    }
    @Override
    public String getTileName() {
        return "vampire";
    }

    @Override
    public void move(int dx, int dy){
        super.move(dx,dy);
        super.move(dx,dy);
    }
}
