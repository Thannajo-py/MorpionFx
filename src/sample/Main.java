package sample;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    Player p1 = new Player("Player1","X","py.png");
    Player p2 = new Player("Player2", "O", "java.png");
    Game game = new Game();
    Interface application = new Interface();

    @Override
    public void start(Stage primaryStage) throws Exception{
        application.init(p1,p2,game);
        Scene scene = new Scene(application.getStack(), 360, 500);
        primaryStage.setTitle("Morpion");
        scene.getStylesheets().add("sample/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

