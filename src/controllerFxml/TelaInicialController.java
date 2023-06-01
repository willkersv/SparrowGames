package controllerFxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TelaInicialController implements Initializable{
    
    @FXML
    private ImageView usuImg;

    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        
        if(Main.usuImg != null){
            System.out.println(Main.usuImg);
            System.out.println(Main.emailIdent);
            Image usuImage = new Image(Main.usuImg);
            System.out.println("aaaaaaaaaaaa");
            usuImg.setImage(usuImage);
        }
        

    }
}
