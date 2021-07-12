package sample;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Game {
    public void checkVictory(Label prompt, GridPane grid, int turn, ArrayList<Button> listOfCase, boolean player1, StackPane w){
        for(int j = 0; j < 3; j++){
            if (!listOfCase.get(3*j).getText().isEmpty() &&
                    listOfCase.get(3*j).getText().equals(listOfCase.get(3*j+1).getText()) &&
                    listOfCase.get(3*j+1).getText().equals(listOfCase.get(3*j+2).getText())){
                if (player1){
                    prompt.setText("Player1 Win!");
                }
                else{
                    prompt.setText("Player2 Win!");
                }
                for (int i = 0; i < 3; i++) {
                    listOfCase.get(3 * j + i).getStyleClass().add("win");
                }
                disableGrid(grid);
                return;
            }
            if (!listOfCase.get(j).getText().isEmpty() &&
                    listOfCase.get(j).getText().equals(listOfCase.get(j+3).getText()) &&
                    listOfCase.get(j+3).getText().equals(listOfCase.get(j+6).getText())){
                if (player1){
                    prompt.setText("Player1 Win!");
                }
                else{
                    prompt.setText("Player2 Win!");
                }
                for (int i = 0; i < 3; i++) {
                    listOfCase.get(j + 3 * i).getStyleClass().add("win");
                }
                disableGrid(grid);
                return;
            }
        }
        if (!listOfCase.get(0).getText().isEmpty() &&
                listOfCase.get(0).getText().equals(listOfCase.get(4).getText()) &&
                listOfCase.get(4).getText().equals(listOfCase.get(8).getText())){
            if (player1){
                prompt.setText("Player1 Win!");
            }
            else{
                prompt.setText("Player2 Win!");
            }
            for (int i = 0; i < 3; i++) {
                listOfCase.get(4 * i).getStyleClass().add("win");
            }
            disableGrid(grid);
            return;
        }
        if (!listOfCase.get(6).getText().isEmpty() &&
                listOfCase.get(6).getText().equals(listOfCase.get(4).getText()) &&
                listOfCase.get(4).getText().equals(listOfCase.get(2).getText())){
            if (player1){
                prompt.setText("Player1 Win!");
            }
            else{
                prompt.setText("Player2 Win!");
            }
            for (int i = 0; i < 3; i++) {
                listOfCase.get(2 + 2 * i).getStyleClass().add("win");
            }
            disableGrid(grid);
            return;
        }


        if (turn == 9) {
            prompt.setText("Egalité!");
            disableGrid(grid);

        }

    }
    private void disableGrid(GridPane grid){
        for (Node b:grid.getChildren()){
            b.setDisable(true);
        }
    }
}
