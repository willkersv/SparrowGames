package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.print.attribute.standard.PrinterIsAcceptingJobs;

import controller.ControlJogo;
import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class TelaJogoController implements Initializable {

    //public static int idJogo;
    private double x = 0, y = 0;

    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;
    
    @FXML
    private Pane barra;

    //Campos do jogo
    @FXML
    private ImageView imgJogo;

    @FXML
    private Label labelNomeJogo;

    @FXML
    private Label descJogo;

    @FXML
    private Label desenvolvedora;

    @FXML
    private Label preco;
    
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
        infoJogo();
        
    }
    

    private void infoJogo(){
        
        ControlJogo cj = new ControlJogo();
        labelNomeJogo.setText(cj.consultaJogo(Main.idJogoAux).getNomeJogo());
        descJogo.setText(cj.consultaJogo(Main.idJogoAux).getDescricao());
        desenvolvedora.setText(cj.consultaJogo(Main.idJogoAux).getDesenvolvedora());
        preco.setText(cj.consultaJogo(Main.idJogoAux).getPrecoJogo().toString());

        Image image = new Image(cj.consultaJogo(Main.idJogoAux).getImgJogo());
        System.out.println(cj.consultaJogo(Main.idJogoAux).getImgJogo());
        imgJogo.setImage(image);
        imgJogo.setPreserveRatio(true);
        imgJogo.setSmooth(true); 
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
