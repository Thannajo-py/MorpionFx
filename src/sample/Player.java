package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Player {
    private String name;
    private String symbol;
    private String image;

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
}
