package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Jogo;

public class ModeloBuscaController implements Initializable{

    @FXML
    private Label precoJogo;

    @FXML
    private Rectangle imgJogo;

    @FXML
    private Label nomeJogo;

    public static int idJogo;
    
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
    }

     public void setData(Jogo jogo){
        try {
            System.out.println(jogo.getIdJogo());
            Image usuImage = new Image(jogo.getImgJogo());
            imgJogo.setFill(new ImagePattern(usuImage));
            nomeJogo.setText(jogo.getNomeJogo());
            precoJogo.setText(jogo.getPrecoJogo().toString());
            idJogo = jogo.getIdJogo();
            
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }

}
