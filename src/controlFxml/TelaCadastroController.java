package controlFxml;

import java.io.IOException;

import controller.ControlUsuario;
import javafx.event.ActionEvent;
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
    protected void btFechar(){
        Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btCadastrar(ActionEvent e) throws IOException{
        Usuario us = new Usuario();
        ControlUsuario cu = new ControlUsuario();
        if(tfSenhaUsuario.getText().equals(tfConfirmaSenhaUsuario.getText())){
            us.setNomeUsuario(tfNomeUsuario.getText());
            us.setEmailUsuario(tfEmailUsuario.getText());
            us.setSenhaUsuario(tfSenhaUsuario.getText());
            cu.cadastrarUsuario(us);
            SceneController sc = new SceneController();
            sc.switchTelaInicial(e);
        }
        else{
            System.out.println("Senha errada, fera");
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

