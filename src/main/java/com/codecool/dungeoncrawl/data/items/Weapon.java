package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class Weapon extends Item{
    private final int plusDamage;
    private String name;

    public Weapon(Cell cell, String name, int damage) {
        super(cell, name);
        this.plusDamage=damage;
    }

    public int getPlusDamage() {
        return plusDamage;
    }
}

