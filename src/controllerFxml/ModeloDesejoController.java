package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
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

    //     coracao.setOnMouseClicked((MouseEvent e)->{
    //         SceneController sc = new SceneController();
    //         ControlCarrinho cc = new ControlCarrinho();
    //         try{
    //             cc.removerJogoCarrinho(idJogo);
    //             sc.switchTelaCarrinho(e);
    //         } catch(IOException e1){
    //             e1.printStackTrace();
    //         }
    //     });
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
