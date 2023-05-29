package controlFxml;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void start(Stage Stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/telaLogin.fxml"));
        Parent root = fxmlLoader.load();
        Scene TelaLogin = new Scene(root);
        Stage.initStyle(StageStyle.UNDECORATED);
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
                Stage.setX(event.getScreenX() - xOffset);
                Stage.setY(event.getScreenY() - yOffset);

            }
        });
        Stage.setScene(TelaLogin);
        Stage.show();
    }

    
}

