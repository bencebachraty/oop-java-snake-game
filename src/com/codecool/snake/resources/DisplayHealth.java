package com.codecool.snake.resources;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.Snake;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class DisplayHealth {

    private static Game game;
    private static Text text = new Text();

    public DisplayHealth() {
        game = Globals.getInstance().game;

        text.setText("Health: " + 100);
        text.setX(40);
        text.setY(50);
        text.setFill(Color.BLUE);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        game.getChildren().add(text);
    }

    public static void updateHealth() {
        text.setText("Health: " + Snake.getHealth());
    }

}
