package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControlCarrinho;
import controller.ControlDesejo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.StageStyle;
import model.Desejo;

public class ModeloDesejoController implements Initializable{

    @FXML
    private Label precoJogo;

    @FXML
    private Rectangle imgJogo;

    @FXML
    private Label nomeJogo;

    @FXML
    private ImageView coracao;

    @FXML
    private ImageView imgCarrinho;

    @FXML
    private ImageView imgHeartFull;

     private int idJogo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        imgJogo.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try { 
                Main.idJogoAux = idJogo;
                sc.switchTelaJogo(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        nomeJogo.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                Main.idJogoAux = idJogo;
                sc.switchTelaJogo(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        imgHeartFull.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            
            try { 
                Main.idJogoAux = idJogo;
                ControlDesejo cd = new ControlDesejo();
                cd.excluiDesejo();
                sc.switchTelaDesejo(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        imgCarrinho.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            
            try { 
                Main.idJogoAux = idJogo;
                ControlDesejo cd = new ControlDesejo();
                ControlCarrinho cc = new ControlCarrinho();
                cc.addJogoCarrinho();
                cd.excluiDesejo();
                Alert a = new Alert(AlertType.CONFIRMATION);
                a.initStyle(StageStyle.UNDECORATED);
                a.setContentText("O jogo foi adicionado ao carrinho de compras");
                a.show();
                sc.switchTelaDesejo(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

     }

     public void setData(Desejo desejo){
        try {
            Image jogoImage = new Image(desejo.getImgJogo());
            imgJogo.setFill(new ImagePattern(jogoImage));
            nomeJogo.setText(desejo.getNomeJogo());
            precoJogo.setText(desejo.getPrecoJogo().toString());
            idJogo = desejo.getIdJogo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
