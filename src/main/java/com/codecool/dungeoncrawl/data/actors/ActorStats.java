package com.codecool.dungeoncrawl.data.actors;

public enum ActorStats {
    PLAYER(10,0,0),
    SKELETON(10,2,2),
    GOLEM(15,3,1),
    VAMPIRE(5,1,4);

    private final int health;
    private final int damage;
    private final int vision;

    ActorStats(int health, int damage, int vision) {
        this.health = health;
        this.damage = damage;
        this.vision = vision;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getVision() {
        return vision;
    }
}
