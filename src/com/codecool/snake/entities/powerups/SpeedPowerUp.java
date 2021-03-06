package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class SpeedPowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public SpeedPowerUp(int randChoice){
        double[] sectionsX = {rnd.nextDouble() * 550, rnd.nextDouble() * (1200 - 650) + 650};
        setImage(Globals.getInstance().getImage("PowerUpSpeed"));
        setX(sectionsX[randChoice]);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    public SpeedPowerUp(){
        setImage(Globals.getInstance().getImage("PowerUpSpeed"));

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
            new SpeedPowerUp();
        }
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
