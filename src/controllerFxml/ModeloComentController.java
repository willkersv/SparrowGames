package controllerFxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public void setData(Comentario comentario){
        try {
            Image usuImage = new Image(comentario.getImgUsuario());
            fotoComentario.setFill(new ImagePattern(usuImage));
            nomeUsuario.setText(comentario.getNomeUsuario());
            lbComentario.setText(comentario.getComentario());
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
   
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
      
}
