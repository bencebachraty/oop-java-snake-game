package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Laser;


public abstract class Enemy extends GameEntity{
    private final int damage;

    public Enemy(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void checkForLaser(GameEntity entity) {
        if (entity instanceof Laser) {
            destroy();
            if (this instanceof SlowDownEnemy) {
                new SlowDownEnemy();
            } else if (this instanceof SizeReduceEnemy) {
                new SizeReduceEnemy();
            } else if (this instanceof SimpleEnemy) {
                new SimpleEnemy();
            }
        }
    }
}
