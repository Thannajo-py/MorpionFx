package sample;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Interface {
    Label prompt = new Label();
    GridPane grid = new GridPane();
    StackPane stack = new StackPane();

    public void init(Player p1, Player p2, Game game){
        VBox root = new VBox();

        StackPane input = new StackPane();
        VBox input2 = new VBox();
        Label p1Name = new Label("Entrez nom joueur 1");
        TextField p1Input = new TextField();
        Label p2Name = new Label("Entrez nom joueur 2");
        TextField p2Input = new TextField();
        HBox buttons = new HBox(10);
        Button valid = new Button("Valid");
        valid.setOnAction(actionEvent -> {
            if (!p1Input.getText().isEmpty()){
                p1.setName(p1Input.getText());
            }
            if(!p2Input.getText().isEmpty()){
                p2.setName(p2Input.getText());
            }
            if (game.isPlayer1()){
                this.prompt.setText(p1.getName() + " your turn!");
            }
            else {
                this.prompt.setText(p2.getName() + " your turn!");
            }
            this.stack.getChildren().remove(input);
        });
        Button cancel = new Button("Cancel");
        cancel.setOnAction(actionEvent -> this.stack.getChildren().remove(input));
        buttons.getChildren().addAll(valid,cancel);
        HBox.setMargin(valid,new Insets(10));
        HBox.setMargin(cancel,new Insets(10,10,10,0));

        input2.getChildren().addAll(p1Name, p1Input, p2Name, p2Input, buttons);

        Rectangle rec = new Rectangle(0,0,360,200);
        rec.setFill(Color.WHITE);
        input.getChildren().addAll(rec,input2);
        root.getChildren().add(makeMenu(input, input2, p1, game));
        for (int i = 0; i < 9; i++) {
            Button square = new Button();
            square.setOnAction(actionEvent -> {
                if (square.getText().isEmpty()) {
                    if (game.isPlayer1()) {
                        p1.play(square, this.prompt, p2);
                    }
                    else {
                        p2.play(square, prompt, p1);
                    }
                    game.passATurn(this.prompt,this.grid);
                }
                else{
                    if (game.isPlayer1()) {
                        this.prompt.setText("Case already fill, " + p1.getName() + " your turn!");
                    }
                    else{
                        this.prompt.setText("Case already fill, " + p2.getName() + " your turn!");
                    }
                }
            });

            square.setMinHeight(120);
            square.setMinWidth(120);
            GridPane.setRowIndex(square,i%3);
            GridPane.setColumnIndex(square,(int)(i/3));
            this.grid.getChildren().add(square);
            game.addASquare(square);
        }
        root.getChildren().add(this.grid);
        VBox prompter = new VBox();
        this.prompt.setText(p1.getName() + " your turn to play!");
        prompter.setAlignment(Pos.CENTER);
        prompter.setMinHeight(100);
        prompter.getChildren().add(this.prompt);
        prompter.setCenterShape(true);
        root.getChildren().add(prompter);
        this.stack.getChildren().add(root);
    }

    public StackPane getStack(){
        return this.stack;
    }

    private MenuBar makeMenu(StackPane input, VBox input2, Player p1, Game game){
        MenuBar menuBar = new MenuBar();
        menuBar.getStyleClass().add("menu");
        Menu file = new Menu("File");
        file.getStyleClass().add("menu");

        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(actionEvent-> Platform.exit());

        MenuItem reset = new MenuItem("New Game");
        reset.setOnAction(actionEvent -> {
            game.reset();
            this.prompt.setText(p1.getName() + " your turn to play!");
        });

        MenuItem settings = new MenuItem("Settings");
        settings.setOnAction(actionEvent -> {
            input2.setAlignment(Pos.CENTER);
            this.stack.getChildren().add(input);
            input.setLayoutY(100);
        });

        file.getItems().addAll(reset, settings, new SeparatorMenuItem(), quit);

        menuBar.getMenus().addAll(file);

        return menuBar;

    }


}
