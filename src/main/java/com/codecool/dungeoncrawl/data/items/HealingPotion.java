package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class HealingPotion extends Item {
    private final int amountToHeal;
    public HealingPotion(Cell cell, String name, int amountToHeal) {
        super(cell, name);
        this.amountToHeal = amountToHeal;
    }

    public int getAmountToHeal() {
        return amountToHeal;
    }
}
