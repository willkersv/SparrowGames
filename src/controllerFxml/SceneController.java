package controllerFxml;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    
    public void switchTelaInicial(MouseEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/view/telaInicial.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTelaJogo(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/telaJogo.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


//Telas relacionadas ao carrinho/compra
    public void switchTelaCarrinho(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/telaCarrinho.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTelaPagamento(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/telaPagamento.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Telas do modal
    public void switchTelaConta(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/telaConta.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTelaBiblioteca(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/telaBiblioteca.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTelaDesejo(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/telaDesejo.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTelaAdmin(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/telaAdmin.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTelaBusca(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/telaBusca.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}
