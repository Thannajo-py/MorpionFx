package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Player {
    private String name;
    final private String symbol;
    final private String image;

    public Player(String name, String symbol, String image) {
        this.name = name;
        this.symbol = symbol;
        this.image = image;
    }
    public String getName() {
        return name;
    }

    private String getSymbol() {
        return this.symbol;
    }

    private String getImage() {
        return this.image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void play(Button square, Label prompt, Player otherPlayer){
        square.setText(this.getSymbol());
        ImageView imageView = new ImageView(getClass().getResource(this.getImage()).toExternalForm());
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        square.setGraphic(imageView);
        prompt.setText(otherPlayer.getName() + " your turn!");
    }

    public void aiPlay(Game game, Label prompt, Player otherPlayer)
    {/* make the IA play following the following pattern: play the winning possibility if any, else avoid the winning possibility of the player else play something else
		IN:turn number, the grid
		OUT: modify the grid accordingly*/
        ArrayList<Button> table = game.getListOfSquare();
        if (game.getTurn() < 2)
        {
            if (table.get(4).getText().isEmpty())
            {
                play(table.get(4), prompt, otherPlayer);
            }
            else
            {
                play(table.get(0), prompt, otherPlayer);
            }
        }
        else
        {
            aiThink(game, prompt, otherPlayer);
        }
    }

    public void aiThink(Game game, Label prompt, Player otherPlayer)
    {/* make the IA play the winning possibility if any, else avoid the winning possibility of the player.
		IN:the string to check(either the IA sign to win or the player sign not to lose), the grid
		OUT: if one of the case is reached play and return true else return false*/
        ArrayList<Button> table = game.getListOfSquare();
        int choiceSlot = -1;
        int choiceValue = -1;
        for (int line = 0; line < 3; line++)
        {
            int lineValue = 0;
            int lineEmptySlot = -1;

            int colValue = 0;
            int colEmptySlot = -1;

            int diagonalValue = 0;
            int diagonalEmptySlot = -1;

            int rDiagonalValue = 0;
            int rDiagonalEmptySlot = -1;

            for (int data = 0; data < 3; data++)
            {
                String actualLineCell = table.get(3 * line + data).getText();
                String actualColCell = table.get(line + 3 * data).getText();

                if (line==0) {
                    String actualDiagonalCell = table.get(line + 4 * data).getText();
                    String actualReverseDiagonalCell = table.get(2 + 2 * data).getText();

                    if (actualDiagonalCell.equals(this.symbol))//checking diagonal
                    {
                        diagonalValue++;
                    } else if (actualDiagonalCell.isEmpty()) {
                        diagonalEmptySlot = line + 4 * data;
                    } else {
                        diagonalValue -= 10;
                    }

                    if (actualReverseDiagonalCell.equals(this.symbol))//checking reverse diagonal
                    {
                        rDiagonalValue++;
                    } else if (actualReverseDiagonalCell.isEmpty()) {

                        rDiagonalEmptySlot = 2 + 2 * data;
                    } else {
                        rDiagonalValue -= 10;
                    }
                }

                if (actualLineCell.equals(this.symbol))//checking line
                {
                    lineValue++;
                }
                else if (actualLineCell.isEmpty())
                {
                    lineEmptySlot = 3 * line + data;
                }
                else
                {
                    lineValue -= 10;
                }

                if (actualColCell.equals(this.symbol))//checking col
                {
                    colValue++;
                }
                else if (actualColCell.isEmpty())
                {
                    colEmptySlot = line + 3 * data;
                }
                else
                {
                    colValue -= 10;
                }
            }
            int[][] listOfValue = {{lineValue, lineEmptySlot}, {colValue, colEmptySlot}, {diagonalValue, diagonalEmptySlot},
                    {rDiagonalValue, rDiagonalEmptySlot}};
            for (int[] value: listOfValue) {
                if (value[0] == 2) {
                    this.play(table.get(value[1]), prompt, otherPlayer);
                    return;
                } else if (value[0] == -20) {
                    choiceValue = value[0];
                    choiceSlot = value[1];
                } else if (choiceValue != -20 && value[0] == 1) {
                    choiceValue = value[0];
                    choiceSlot = value[1];
                } else if (choiceValue != 1 && choiceValue != -20 && value[0] == 0 && value[1] != -1) {
                    choiceValue = value[0];
                    choiceSlot = value[1];
                } else if (choiceValue == -1 && value[1] != -1) {
                    choiceSlot = value[1];
                }
            }

        }
        this.play(table.get(choiceSlot), prompt, otherPlayer);
    }

}
