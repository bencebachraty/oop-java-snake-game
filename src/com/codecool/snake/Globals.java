package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;
import javafx.stage.Stage;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;
    public static Stage primaryStage;

    public static final double WINDOW_WIDTH = 1300;
    public static final double WINDOW_HEIGHT = 730;

    public Display display;
    public Game game;

    public static GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_head.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("PowerUpBerry", new Image("powerup_berry.png"));
        resources.addImage("SlowDownEnemy", new Image("snowflake.png"));
        resources.addImage("PowerUpApple", new Image("powerup_apple.png"));
        resources.addImage("PowerUpSpeed", new Image("powerup_speed.png"));
        resources.addImage("SizeReduceEnemy", new Image("size_reduce_enemy.png"));
        resources.addImage("Laser", new Image("laser.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
