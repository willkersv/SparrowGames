package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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


public class TelaInicialController implements Initializable{
    
    double x = 0, y = 0;
    
    @FXML
    private Circle circleUsu;

    @FXML
    private Label nomeUsuario;

    @FXML
    private ImageView imgCarrinho;

    @FXML
    private Pane barra;

    @FXML
    private Button btnTW;

    @FXML
    protected void btTW(ActionEvent e) throws IOException{
        System.out.println("saaaaaaaaaaaaaaaaaaa");
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

        imgCarrinho.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                //mudar para tela carrinho
                sc.switchTelaLogin(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });

        if(Main.usuImg != null){
            //System.out.println(Main.usuImg);
            //System.out.println(Main.emailIdent);
            Image usuImage = new Image(Main.usuImg, false);
            circleUsu.setStroke(Color.WHITE);
            circleUsu.setFill(new ImagePattern(usuImage));

        }
        else{
            Image usuImage = new Image("/images/user.png");
            circleUsu.setStroke(Color.BLACK);
            circleUsu.setFill(new ImagePattern(usuImage));
        }
        //faz nome aparecer ao lado da fotinho :) 
        nomeUsuario.setText(Main.nomeUsuario);
    }


}
