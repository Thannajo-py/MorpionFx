package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import javax.swing.*;
import java.util.ArrayList;

public class Main extends Application {
    boolean player1 = true;
    Player p1 = new Player();
    Player p2 = new Player();
    Label prompt = new Label();
    GridPane grid = new GridPane();
    int turn = 0;
    Game game = new Game();
    StackPane window = new StackPane();
    ArrayList<Button> listOfCase = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{

        VBox root = new VBox(10);
        MenuBar menuBar = new MenuBar();
        p1.setName("Player1");
        p2.setName("Player2");
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
            if (player1){
                prompt.setText(p1.getName() + " your turn!");
            }
            else {
                prompt.setText(p2.getName() + " your turn!");
            }
            window.getChildren().remove(input);
        });
        Button cancel = new Button("Cancel");
        cancel.setOnAction(actionEvent -> {
            window.getChildren().remove(input);
        });
        buttons.getChildren().addAll(valid,cancel);
        HBox.setMargin(valid,new Insets(10));
        HBox.setMargin(cancel,new Insets(10,10,10,0));
        input2.getChildren().addAll(p1Name, p1Input, p2Name, p2Input, buttons);
        Rectangle rec = new Rectangle(0,0,360,200);
        rec.setFill(Color.WHITE);
        input.getChildren().addAll(rec,input2);
        Menu file = new Menu("File");
        menuBar.getStyleClass().add("menu");
        file.getStyleClass().add("menu");
        MenuItem settings = new MenuItem("Settings");
        MenuItem quit = new MenuItem("Quit");
        MenuItem reset = new MenuItem("New Game");

        settings.setOnAction(actionEvent -> {

            input2.setAlignment(Pos.CENTER);
            window.getChildren().add(input);
            input.setLayoutY(100);


        });
        quit.setOnAction(actionEvent-> Platform.exit());
        file.getItems().addAll(reset, settings, new SeparatorMenuItem(), quit);
        menuBar.getMenus().addAll(file);
        root.getChildren().add(menuBar);
        for (int i = 0; i < 9; i++) {
            Button square = new Button();
            square.setOnAction(actionEvent -> {
                if (square.getText().isEmpty()) {
                    if (player1) {
                        square.setText("X");
                        ImageView imageView = new ImageView(getClass().getResource("py.png").toExternalForm());
                        imageView.setFitHeight(100);
                        imageView.setFitWidth(100);
                        square.setGraphic(imageView);
                        prompt.setText(p2.getName() + " your turn!");
                    } else {
                        square.setText("O");
                        ImageView imageView = new ImageView(getClass().getResource("java.png").toExternalForm());
                        imageView.setFitHeight(100);
                        imageView.setFitWidth(100);
                        square.setGraphic(imageView);
                        prompt.setText(p1.getName() + " your turn!");
                    }
                    turn++;
                    game.checkVictory(prompt,grid,turn,listOfCase,player1,window);
                    player1 = !player1;
                }
                else{
                    if (player1) {
                        prompt.setText("Case already fill, " + p1.getName() + " your turn!");
                    }
                    else{
                        prompt.setText("Case already fill, " + p2.getName() + " your turn!");
                    }
                }
            });
            reset.setOnAction(actionEvent -> {
                for (Button b:listOfCase){
                    b.setText("");
                    b.setGraphic(null);
                    b.setDisable(false);
                    b.getStyleClass().remove("win");
                }
                player1 = true ;
                turn = 0;
                prompt.setText(p1.getName() + " your turn to play!");
            });
            square.setMinHeight(120);
            square.setMinWidth(120);
            GridPane.setRowIndex(square,i%3);
            GridPane.setColumnIndex(square,(int)(i/3));
            grid.getChildren().add(square);
            listOfCase.add(square);
        }
        root.getChildren().add(grid);
        VBox prompter = new VBox();
        prompt.setText(p1.getName() + " your turn to play!");
        prompter.setAlignment(Pos.CENTER);
        prompter.setMinHeight(100);
        prompter.getChildren().add(prompt);
        prompter.setCenterShape(true);
        root.getChildren().add(prompter);
        primaryStage.setTitle("Morpion");
        window.getChildren().add(root);
        Scene scene = new Scene(window, 360, 500);
        scene.getStylesheets().add("sample/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

