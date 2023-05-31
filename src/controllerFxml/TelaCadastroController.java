package controllerFxml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControlUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Usuario;

public class TelaCadastroController implements Initializable{

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnFechar;

    @FXML
    private PasswordField tfConfirmaSenhaUsuario;

    @FXML
    private TextField tfEmailUsuario;

    @FXML
    private TextField tfNomeUsuario;

    @FXML
    private TextField tfSenhaUsuario;

    @FXML
    private ImageView noPhoto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        noPhoto.setOnMouseClicked((MouseEvent e)->{
            selecionaFoto();
        });

    }

    public void selecionaFoto(){
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new ExtensionFilter("Imagens","*.png", "*.jpg", "*.jpeg")); 
        File file = f.showOpenDialog(new Stage());
        noPhoto.setImage(new Image(file.getAbsolutePath()));
    }

    

    @FXML
    protected void btFechar(){
        Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btCadastrar(ActionEvent e) throws IOException{
        Usuario us = new Usuario();
        ControlUsuario cu = new ControlUsuario();
        boolean confirma;
        if(tfSenhaUsuario.getText().equals(tfConfirmaSenhaUsuario.getText())){
            us.setNomeUsuario(tfNomeUsuario.getText());
            us.setEmailUsuario(tfEmailUsuario.getText());
            us.setSenhaUsuario(tfSenhaUsuario.getText());
            confirma = cu.cadastrarUsuario(us);
            if(confirma == true){
                //Fecha o Pop-UP
                Stage stage = (Stage) btnFechar.getScene().getWindow();
                stage.close();
            }
            else{
                System.out.println("f");
            }
            
        }
        else{
            System.out.println("Senhas diferentes, fera");
        }
        
        
    }

    



    // protected void btAlterar(){
    //     ControlUsuario cu = new ControlUsuario();   
    //     if(tfNomeUsuario.getText() != ""){
    //         cu.alterarUsuario(tfNomeUsuario.getText());
    //     }
    //     else{
    //         System.out.println("Coloca um nome ai pow!");
    //     }
    // }
        

}

