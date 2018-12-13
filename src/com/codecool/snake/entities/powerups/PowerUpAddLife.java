package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class PowerUpAddLife extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public PowerUpAddLife(int randChoice){
        double[] sectionsX = {rnd.nextDouble() * 550, rnd.nextDouble() * (1200 - 650) + 650};
        setImage(Globals.getInstance().getImage("PowerUpApple"));
        setX(sectionsX[randChoice]);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    public PowerUpAddLife() {
        setImage(Globals.getInstance().getImage("PowerUpApple"));

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
            new PowerUpAddLife();
        }
    }

    @Override
    public String getMessage() {
        return "Collected 10 more life :)";
    }
}
