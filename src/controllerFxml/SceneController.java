package controllerFxml;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneController {
    public static Stage stage;
    private Scene scene;
    private Parent root;
    
    //dar algum jeito de passar o root por paramaetro para pode mover a tela
    public void switchTelaLogin(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/telaLogin.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTelaLogin2(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/view/telaLogin2.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchTelaCadastro(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/telaCadastro.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        //parte nova
        TelaCadastroController tcc = fxmlLoader.getController();
        Stage stage1 = new Stage();
        stage1.initStyle(StageStyle.UNDECORATED);;
        stage1.setScene(new Scene(root1));
        tcc.setStage(stage1);
        stage1.show();
    }

    public void switchTelaInicial(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/view/telaInicial.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    public void switchTelaJogo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/telaJogo.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // public void switchTelaBiblioteca(ActionEvent event) throws IOException {
    //     root = FXMLLoader.load(getClass().getResource("/view/telaBiblioteca.fxml"));
    //     stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    //     scene = new Scene(root);
    //     stage.setScene(scene);
    //     stage.show();
    // }

    // public void switchTelaCarrinho(MouseEvent event) throws IOException {
    //     root = FXMLLoader.load(getClass().getResource("/view/telaCarrinho.fxml"));
    //     stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    //     scene = new Scene(root);
    //     stage.setScene(scene);
    //     stage.show();
    // }

    // public void switchTelaPagamento(ActionEvent event) throws IOException {
    //     root = FXMLLoader.load(getClass().getResource("/view/telaPagamento.fxml"));
    //     stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    //     scene = new Scene(root);
    //     stage.setScene(scene);
    //     stage.show();
    // }

    // public void switchTelaDesejo(ActionEvent event) throws IOException {
    //     root = FXMLLoader.load(getClass().getResource("/view/telaDesejo.fxml"));
    //     stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    //     scene = new Scene(root);
    //     stage.setScene(scene);
    //     stage.show();
    // }

    
}
