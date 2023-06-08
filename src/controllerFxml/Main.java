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
    public static String nomeUsuario;
    public static String emailIdent;
    public static int idIdent;
    public static String usuImg;
    public static Boolean verAdmin;

    //Guarda dado do jogo
    public static int idJogoAux;
    
    //Torna a tela Draggeble
    private double xOffset = 0;
    private double yOffset = 0;

    public static Scene mainScene;     
    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Image image = new Image("images/sparrow games.png");
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/telaLogin.fxml"));
        Parent root = fxmlLoader.load();
        Scene TelaLogin = new Scene(root);
        mainScene = TelaLogin;
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
        new animatefx.animation.ZoomIn(root).setSpeed(2).play();;
        primaryStage.show();
    }

    
}