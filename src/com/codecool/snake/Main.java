package com.codecool.snake;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.codecool.snake.Globals.primaryStage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        Globals.primaryStage = primaryStage;
        Scene mainScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        game.start();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }

    public static void restart() {
        System.out.println("Starting new game..");
        if (!primaryStage.isFocused()) primaryStage.requestFocus();
        Display.clear();
        Game.init();
        Globals.getInstance().startGame();
    }
}
