package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;
    public static Stage primaryStage;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public Display display;
    public Game game;

    public static GameLoop gameLoop;
    private Resources resources;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static double score;



    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public static void init() {
        score = 0;
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
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
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
