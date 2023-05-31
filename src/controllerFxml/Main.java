package controllerFxml;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application {
    
    //Identificadores do usuario durante toda a utilizacao do sistema
    public static String emailIdent;
    public static Boolean verAdmin;

    //Torna a tela Draggeble
    private double xOffset = 0;
    private double yOffset = 0;
    
    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage) throws IOException {
        Image image = new Image("images/sex.gif");
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/telaLogin.fxml"));
        Parent root = fxmlLoader.load();
        Scene TelaLogin = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(image);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);

            }
        });
        primaryStage.setScene(TelaLogin);
        primaryStage.show();
    }

    
}

