package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class TelaInicialController implements Initializable{
    
    private double x = 0, y = 0;

    //Variavel para o modalzinho
    private boolean permite = true;
    
    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;

    @FXML
    private ImageView imgCarrinho;

    @FXML
    private Pane barra;

    @FXML
    private ImageView btnTW;

    @FXML
    private Pane pnTeste;

    @FXML
    private Button btnConta;


    private void btTW(MouseEvent e) throws IOException{
        System.out.println("saaaaaaaaaaaaaaaaaaa");
        Main.idJogoAux = 2;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        
        barra.setOnMousePressed(mouseEvent ->{
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        barra.setOnMouseDragged(mouseEvent ->{
            SceneController.stage.setX(mouseEvent.getScreenX() - x);
            SceneController.stage.setY(mouseEvent.getScreenY() - y);
        });

        preLoadDados();

        imgCarrinho.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaLogin(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });

        circleUsu.setOnMouseClicked((MouseEvent e)->{
            if(permite == true){
                puxaPane();
                permite = false;
            }
            else{
                voltaPane();
                permite = true;
            }
            
        });

        btnTW.setOnMouseClicked((MouseEvent e)->{
            try {
                btTW(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        

        btnConta.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                Main.idJogoAux = 2;
                sc.switchTelaJogo(e);
            } 
            catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void puxaPane(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(pnTeste);
        slide.setByX(-200);
        slide.play();
        pnTeste.toFront();
    }

    private void voltaPane(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(pnTeste);  
        slide.setByX(200);
        slide.play();
        pnTeste.toFront();
    }

    private void preLoadDados(){
        if(Main.usuImg != null){
            Image usuImage = new Image(Main.usuImg, false);
            circleUsu.setFill(new ImagePattern(usuImage));
        }
        else{
            Image usuImage = new Image("/images/user.png");
            circleUsu.setFill(new ImagePattern(usuImage));
        }
        //Faz nome aparecer ao lado da foto do usuário
        lbNomeUsuario.setText(Main.nomeUsuario);
    }

}
