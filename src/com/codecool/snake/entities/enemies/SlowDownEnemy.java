package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import javafx.geometry.Point2D;

public class SlowDownEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();
    private double direction = rnd.nextDouble() * 360;

    public SlowDownEnemy() {
        super(-10);

        setImage(Globals.getInstance().getImage("SlowDownEnemy"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * 1);

        double direction = rnd.nextFloat() * (210 - 150) + 150;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
            new SlowDownEnemy();
        }
        setRotate(direction);
        direction += 2;
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
            new SlowDownEnemy();
        }
        checkForLaser(entity);
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage and slowed down");
    }
}
