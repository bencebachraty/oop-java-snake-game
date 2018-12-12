package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();
    private Game game;


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        game = Globals.getInstance().game;

        Text text = new Text();
        text.setText("Total Life: ");
        text.setX(40);
        text.setY(50);
        text.setFill(Color.BLUE);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        game.getChildren().add(text);

        init();
    }

    public void init() {
        spawnSnake();
        spawnAddLife(4);
        spawnPowerUps(5);
        spawnEnemies(4);

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
        for(int i = 0; i < numberOfEnemies; ++i) new SlowDownEnemy();
        for(int i = 0; i < numberOfEnemies; ++i) new SizeReduceEnemy();
    }


    private void spawnPowerUps(int numberOfPowerUps) {

        GameEntity speed = new SpeedPowerUp();
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();

    }

    private void spawnAddLife(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new PowerUpAddLife();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
