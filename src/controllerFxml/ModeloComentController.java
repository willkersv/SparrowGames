package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControlComentario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Comentario;

public class ModeloComentController implements Initializable{
    
    //comentario things
    @FXML
    public Circle fotoComentario;
 
    @FXML
    public Label nomeUsuario;

    @FXML
    public Label lbComentario;

    @FXML
    public ImageView lixeira;

    private int idComentario;

    public void setData(Comentario comentario){
        try {
            Image usuImage = new Image(comentario.getImgUsuario());
            fotoComentario.setFill(new ImagePattern(usuImage));
            nomeUsuario.setText(comentario.getNomeUsuario());
            lbComentario.setText(comentario.getComentario());
            idComentario = comentario.getIdComentario();
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
   
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lixeira.setOnMouseClicked((MouseEvent e)->{
            ControlComentario cc = new ControlComentario();
            SceneController sc = new SceneController();
            try{
                cc.deletarComentario(idComentario);
                sc.switchTelaJogo(e);
            } catch(IOException e1){
                e1.printStackTrace();
            }
        });

        if(Main.verAdmin == false){
            lixeira.setVisible(false);
        }
    }
      
}
