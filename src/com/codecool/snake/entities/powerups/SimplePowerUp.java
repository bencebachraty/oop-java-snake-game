package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util .Random;


public class SimplePowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public SimplePowerUp(int randChoice){
        double[] sectionsX = {rnd.nextDouble() * 550, rnd.nextDouble() * (1200 - 650) + 650};
        setImage(Globals.getInstance().getImage("PowerUpBerry"));
        setX(sectionsX[randChoice]);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    public SimplePowerUp() {
        setImage(Globals.getInstance().getImage("PowerUpBerry"));

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
            new SimplePowerUp();
        }
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
