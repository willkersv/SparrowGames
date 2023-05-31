package controllerFxml;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    //dar algum jeito de passar o root por paramaetro para pode mover a tela
    public void switchTelaLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/telaLogin.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTelaLogin2(ActionEvent event) throws IOException, InterruptedException {
        root = FXMLLoader.load(getClass().getResource("/view/telaLogin2.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchTelaCadastro(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/telaCadastro.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);;
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void switchTelaInicial(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/view/telaInicial.fxml"));
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

    // public void switchTelaCarrinho(ActionEvent event) throws IOException {
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
