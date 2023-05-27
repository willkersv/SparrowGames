package controlFxml;

import controller.ControlUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;

public class TelaCadastroController {

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
    private void btFechar(){
        Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btCadastrar(){

        Usuario us = new Usuario();
        ControlUsuario cu = new ControlUsuario();
        if(tfSenhaUsuario.getText().equals(tfConfirmaSenhaUsuario.getText())){
            us.setNomeUsuario(tfNomeUsuario.getText());
            us.setEmailUsuario(tfEmailUsuario.getText());
            us.setSenhaUsuario(tfSenhaUsuario.getText());
            cu.cadastraUsuario(us);
        }
        else{
            System.out.println("senhas diferentes, fera");
        }     
    }
}

