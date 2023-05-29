package controlFxml;

import java.io.IOException;

import controller.ControlUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Usuario;



public class TelaLoginController {

    @FXML
    private Button btLogin;

    @FXML
    private ImageView btSair;

    @FXML
    private Hyperlink hyCadastro;

    @FXML
    private Hyperlink hyEsqueceuSenha;

    @FXML
    private TextField tfEmail;
    
    @FXML
    private PasswordField tfSenha;

    @FXML 
    private Button btnFechar;

    @FXML
    private void btLogar(ActionEvent e) throws IOException {
        Usuario us = new Usuario();
        ControlUsuario cu = new ControlUsuario();
        us.setEmailUsuario(tfEmail.getText());
        us.setSenhaUsuario(tfSenha.getText());
        System.out.println("------------------------\nBOTAO LOGIN AINDA FUNCIONA");
        cu.consultarLogin(us);
        SceneController sc = new SceneController();
        if(Main.emailIdent == null){ 
            sc.switchTelaLogin2(e);
        }else{
            sc.switchTelaInicial(e);
        }
    }

    @FXML
    private void btFechar(){
        Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void hylCadastro(ActionEvent e) throws IOException{
        SceneController sc = new SceneController();
        sc.switchTelaCadastro(e);
    }

}

