package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.SlowDownEnemy;
import com.codecool.snake.entities.enemies.SizeReduceEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.PowerUpAddLife;
import com.codecool.snake.entities.powerups.SpeedPowerUp;

import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Game extends Pane {
    private static Snake snake = null;
    private static GameTimer gameTimer = new GameTimer();
    public static int numOfEn = 5;

    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public static void init() {
        spawnSnake();
        createHealthDisplay();
        spawnPowerUps(5);
        spawnEnemies(numOfEn);

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private static void spawnSnake() {
        snake = new Snake(new Vec2d(600, 400));
    }


    private static void createHealthDisplay() {
        new DisplayHealth();
    }

    private static void spawnEnemies(int numberOfEnemies) {

        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
        for(int i = 0; i < numberOfEnemies; ++i) new SlowDownEnemy();
        for(int i = 0; i < numberOfEnemies; ++i) new SizeReduceEnemy();
    }


    private static void spawnPowerUps(int numberOfPowerUps) {
        if (Snake.getHealth() == 100) {
            int randomFactor = (int)(Math.random() * ((1 - 0) + 1)) + 0;
            new SpeedPowerUp(randomFactor);

            for(int i = 0; i < numberOfPowerUps; ++i) {
                int randomF = (int)(Math.random() * ((1 - 0) + 1)) + 0;
                new SimplePowerUp(randomF);
                new PowerUpAddLife(randomF);
            }
        } else {
            new SpeedPowerUp();
            for(int i = 0; i < numberOfPowerUps; ++i) {
                new SimplePowerUp();
                new PowerUpAddLife();
            }
        }
    }


    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
