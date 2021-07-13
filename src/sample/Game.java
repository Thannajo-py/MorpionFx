package sample;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


import java.util.ArrayList;

public class Game {
    private boolean player1 = true;
    private int turn = 0;
    private ArrayList<Button> listOfSquare = new ArrayList<>();
    private boolean finished = false;
    private void checkVictory(Label prompt, GridPane grid){
        for(int j = 0; j < 3; j++){
            if (this.checkLine(j)){
                for (int i = 0; i < 3; i++) {
                    listOfSquare.get(3 * j + i).getStyleClass().add("win");
                }
                endGame(prompt, grid);
                this.finished = true;
                return;
            }
            if (this.checkColumn(j)){
                for (int i = 0; i < 3; i++) {
                    listOfSquare.get(j + 3 * i).getStyleClass().add("win");
                }
                endGame(prompt, grid);
                this.finished = true;
                return;
            }
        }


        if (this.checkDiagonal()){
            for (int i = 0; i < 3; i++) {
                listOfSquare.get(4 * i).getStyleClass().add("win");
            }
            endGame(prompt, grid);
            this.finished = true;
            return;
        }

        if (this.checkReverseDiagonal()){
            for (int i = 0; i < 3; i++) {
                listOfSquare.get(2 + 2 * i).getStyleClass().add("win");
            }
            endGame(prompt, grid);
            this.finished = true;
            return;
        }

        if (this.turn == 9) {
            prompt.setText("Egalité!");
            disableGrid(grid);
            this.finished = true;
        }
    }

    private void endGame(Label prompt, GridPane grid){
        if (this.player1){
            prompt.setText("Player1 Win!");
        }
        else{
            prompt.setText("Player2 Win!");
        }
        disableGrid(grid);
    }

    private void disableGrid(GridPane grid){
        for (Node b:grid.getChildren()){
            b.setDisable(true);
        }
    }

    public void reset(){
        this.turn = 0;
        this.player1 = true;
        this.finished = false;
        for (Button b:this.listOfSquare){
            b.setText("");
            b.setGraphic(null);
            b.setDisable(false);
            b.getStyleClass().remove("win");
        }
    }

    public void passATurn(Label prompt, GridPane grid){
        this.turn++;
        this.checkVictory(prompt, grid);
        this.changePlayer();
    }

    private void changePlayer (){
        this.player1 = !this.player1;
    }

    public boolean isPlayer1(){
        return this.player1;
    }

    public void addASquare(Button b){
        this.listOfSquare.add(b);
    }

    private boolean checkLine(int j){
        return !listOfSquare.get(3*j).getText().isEmpty() &&
                listOfSquare.get(3*j).getText().equals(listOfSquare.get(3*j+1).getText()) &&
                listOfSquare.get(3*j+1).getText().equals(listOfSquare.get(3*j+2).getText());
    }

    private boolean checkColumn(int j){
        return !listOfSquare.get(j).getText().isEmpty() &&
                listOfSquare.get(j).getText().equals(listOfSquare.get(j+3).getText()) &&
                listOfSquare.get(j+3).getText().equals(listOfSquare.get(j+6).getText());
    }

    private boolean checkDiagonal(){
        return !listOfSquare.get(0).getText().isEmpty() &&
                listOfSquare.get(0).getText().equals(listOfSquare.get(4).getText()) &&
                listOfSquare.get(4).getText().equals(listOfSquare.get(8).getText());
    }

    private boolean checkReverseDiagonal(){
        return !listOfSquare.get(6).getText().isEmpty() &&
                listOfSquare.get(6).getText().equals(listOfSquare.get(4).getText()) &&
                listOfSquare.get(4).getText().equals(listOfSquare.get(2).getText());
    }

    public ArrayList<Button> getListOfSquare() {
        return listOfSquare;
    }

    public int getTurn() {
        return turn;
    }

    public boolean isFinished(){
        return this.finished;
    }
}
