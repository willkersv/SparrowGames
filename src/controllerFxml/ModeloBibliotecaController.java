package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Biblioteca;

public class ModeloBibliotecaController implements Initializable{
    @FXML
    private Label precoJogo;

    @FXML
    private Rectangle imgJogo;

    @FXML
    private Label nomeJogo;

    @FXML
    private Label lbKey;

    @FXML
    private Label lbSuaKey;

    @FXML
    private Button btnKey;

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

        btnKey.setOnMouseClicked((MouseEvent e)->{
            lbKey.setVisible(true);
            lbSuaKey.setVisible(true);
        });
    }

    public void setData(Biblioteca bib){
        try {
            Image jogoImage = new Image(bib.getImgJogo());
            imgJogo.setFill(new ImagePattern(jogoImage));
            nomeJogo.setText(bib.getNomeJogo());
            lbKey.setText(bib.getIdSerial());
            idJogo = bib.getIdJogo();
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
}
