package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.stage.StageStyle;

import java.util.Arrays;
import java.util.Optional;


public class Snake implements Animatable {
    private float speed = 2;

    private int health = 100;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;

    private static Vec2d pos;

    public static Vec2d getPos() {
        return pos;
    }

    public Snake(Vec2d position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();

        head.getPosition();

        addPart(4);
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);

        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();

        this.pos = head.getPosition();

    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void removePart(int numParts) {

        for (int i = 0; i < numParts; i++) {
            GameEntity parent = getLastPart();
            body.remove(parent);
            parent.destroy();
            body.doPendingModifications();
            System.out.println("Destroyed a body part");

        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }


    public void changeHealth(int diff) {
        health += diff;
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            gameOverAlert();
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for (GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if (result != null) return result;
        return head;
    }

    public void slowDownSpeed(float change) {
        speed *= change;
        SnakeHead.setTurnRate(1);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        speed = 2;
                        SnakeHead.setTurnRate(2);
                    }
                },
                3000
        );
    }

    public void changeSpeed() {

        SnakeHead.setTurnRate(SnakeHead.getTurnRate()*2);
        speed = speed * 2;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        speed = 2;
                        SnakeHead.setTurnRate(2);
                    }
                },
                3000
        );
    }

    private void gameOverAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setGraphic(null);
        alert.getDialogPane().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Final score: " + getScore());

        ButtonType buttonTypeRestart = new ButtonType("Restart");
        ButtonType buttonTypeExit = new ButtonType("Exit");
        alert.getButtonTypes().setAll(buttonTypeRestart, buttonTypeExit);

        Platform.runLater(() -> {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeRestart) {
                System.out.println("Starting new game..");
                // restart here
            } else if (result.get() == buttonTypeExit) {
                Platform.exit();
            }
        });
    }

    private int getScore() {
        int score = body.getList().size() - 4;
        if (score < 1) return 0;
        else return score;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}

