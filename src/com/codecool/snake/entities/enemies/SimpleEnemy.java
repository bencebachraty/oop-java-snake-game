package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;



public class SimpleEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();

    public Vec2d posi;

    public void setPosi(Vec2d posi) {
        this.posi = posi;
    }

    public SimpleEnemy() {
        super(-10);

        setImage(Globals.getInstance().getImage("SimpleEnemy"));
        setX(Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextFloat() * (300 - 240) + 240;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
            SimpleEnemy newEn = new SimpleEnemy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
            SimpleEnemy newEn = new SimpleEnemy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
