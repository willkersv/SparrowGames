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

    //Teste kk
    private static Stage stage;
    private static Scene TelaLogin;
    private static Scene TelaCadastro;
    
    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/telaLogin.fxml"));
        Parent root = fxmlLoader.load();
        //Scene tela = new Scene(root);
        TelaLogin = new Scene(root);
        
        
        //tela de cadastro
        FXMLLoader fxmlLoader2 = new FXMLLoader(this.getClass().getResource("/view/telaCadastro.fxml"));
        Parent root2 = fxmlLoader2.load();
        //Scene tela = new Scene(root);
        TelaCadastro = new Scene(root2);
        
        stage.initStyle(StageStyle.UNDECORATED);
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

    public static void changeScreen(String scr){
        switch(scr){
            case "login":
                stage.setScene(TelaLogin);
                break;
            case "TelaCadastro":
                stage.setScene(TelaCadastro);
                break;
        }
    }
    
}

