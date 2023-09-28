package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class Weapon extends Item{
    private final int damageModifier;

    public Weapon(Cell cell, String name, int damage) {
        super(cell, name);
        this.damageModifier =damage;
    }

    public int getDamageModifier() {
        return damageModifier;
    }
}

