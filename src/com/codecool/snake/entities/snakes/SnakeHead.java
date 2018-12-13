package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.SlowDownEnemy;
import com.codecool.snake.entities.powerups.PowerUpAddLife;
import com.codecool.snake.entities.enemies.SizeReduceEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;

import com.codecool.snake.entities.powerups.SpeedPowerUp;
import com.codecool.snake.DisplayHealth;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;


public class SnakeHead extends GameEntity implements Interactable {
    private static float turnRate = 2;

    private Snake snake;
    private static Vec2d headPosition = new Vec2d(0,0);
    private static double direction;

    public static float getTurnRate() {
        return turnRate;
    }

    public static void setTurnRate(float turnRate) {
        SnakeHead.turnRate = turnRate;
    }

    public SnakeHead(Snake snake, Vec2d position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }

    public void updateRotation(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }

        direction = headRotation;
        headPosition.x = getX();
        headPosition.y = getY();

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof Enemy){
            snake.changeHealth(((Enemy) entity).getDamage());
            DisplayHealth.updateHealth();
            if (entity instanceof SizeReduceEnemy) {
                System.out.println(getMessage());
                snake.removePart(3);
            } else if (entity instanceof SlowDownEnemy) {
                System.out.println(getMessage());
                snake.changeSpeed(0.5f);
            } else {
                System.out.println(getMessage());
            }
        }
        if (entity instanceof SimplePowerUp){
            System.out.println(getMessage());
            snake.addPart(4);
        }
        if (entity instanceof PowerUpAddLife){
            System.out.println(getMessage());
            snake.changeHealth(10);
            DisplayHealth.updateHealth();
        }
        if (entity instanceof SpeedPowerUp) {
            System.out.println(getMessage());
            snake.boostSpeed();
        }
    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }

    public static double getDirection() {
        return direction;
    }

    public static Vec2d getHeadPosition() {
        return headPosition;
    }
}
