package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class TelaInicialController implements Initializable{
    
    private double x = 0, y = 0;

    @FXML
    private Pane barra;
    
    //Variavel para o modalzinho
    private boolean permite = true;
    
    //pre load de dados
    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;

    @FXML
    private ImageView imgCarrinho;
    
    //Modal things
    @FXML
    private Pane pnModal;

    @FXML
    private Button btnTelaConta;
    
    @FXML
    private Button btnTelaBiblioteca;

    @FXML
    private Button btnTelaFavoritos;

    @FXML
    private Button btnTelaAdmin;


    //Image view dos jogos da tela
    @FXML
    private ImageView imgTL;

    @FXML
    private ImageView imgER;

    @FXML
    private ImageView imgTW;

    @FXML
    private ImageView imgDS;

    @FXML
    private ImageView imgBB;

    @FXML
    private ImageView imgFS;

    @FXML
    private ImageView imgMK;

    @FXML
    private ImageView imgWD;


    private void TelaTL(MouseEvent e) throws IOException{
        Main.idJogoAux = 1;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }

    private void TelaER(MouseEvent e) throws IOException{
        Main.idJogoAux = 4;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }

    private void TelaTW(MouseEvent e) throws IOException{
        Main.idJogoAux = 2;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e);
    }

    private void TelaDS(MouseEvent e) throws IOException{
        Main.idJogoAux = 5;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }

    private void TelaBB(MouseEvent e) throws IOException{
        Main.idJogoAux = 6;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }

    private void TelaFS(MouseEvent e) throws IOException{
        Main.idJogoAux = 7;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }

    private void TelaMK(MouseEvent e) throws IOException{
        Main.idJogoAux = 8;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }

    private void TelaWD(MouseEvent e) throws IOException{
        Main.idJogoAux = 9;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        
        barra.setOnMousePressed(mouseEvent ->{
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        barra.setOnMouseDragged(mouseEvent ->{
            SceneController.stage.setX(mouseEvent.getScreenX() - x);
            SceneController.stage.setY(mouseEvent.getScreenY() - y);
        });

        preLoadDados();

        imgCarrinho.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaLogin(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });

        circleUsu.setOnMouseClicked((MouseEvent e)->{
            if(permite == true){
                puxaModal();
                permite = false;
            }
            else{
                voltaModal();
                permite = true;
            }
            
        });

        //imagens para ir para os jogos
        imgTL.setOnMouseClicked((MouseEvent e)->{
                try {
                    TelaTL(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            
        });

        imgER.setOnMouseClicked((MouseEvent e)->{
            try {
                TelaER(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });

        imgTW.setOnMouseClicked((MouseEvent e)->{
            try {
                TelaTW(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
    
        });

        imgDS.setOnMouseClicked((MouseEvent e)->{
            try {
                TelaDS(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        imgBB.setOnMouseClicked((MouseEvent e)->{
            try {
                TelaBB(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        imgFS.setOnMouseClicked((MouseEvent e)->{
            try {
                TelaFS(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        imgMK.setOnMouseClicked((MouseEvent e)->{
            try {
                TelaMK(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        imgWD.setOnMouseClicked((MouseEvent e)->{
            try {
                TelaWD(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        //Botões do modal
        // btnTelaConta.setOnMouseClicked((MouseEvent e)->{
        //     SceneController sc = new SceneController();
        //     try {
        //         sc.switchTelaConta(e);
        //     } 
        //     catch (IOException e1) {
        //         e1.printStackTrace();
        //     }
        // });

        // btnTelaBiblioteca.setOnMouseClicked((MouseEvent e)->{
        //     SceneController sc = new SceneController();
        //     try {
        //         sc.switchTelaBiblioteca(e);
        //     } 
        //     catch (IOException e1) {
        //         e1.printStackTrace();
        //     }
        // });

        // btnTelaFavoritos.setOnMouseClicked((MouseEvent e)->{
        //     SceneController sc = new SceneController();
        //     try {
        //         sc.switchTelaFavoritos(e);
        //     } 
        //     catch (IOException e1) {
        //         e1.printStackTrace();
        //     }
        // });

        // btnTelaAdmin.setOnMouseClicked((MouseEvent e)->{
        //     SceneController sc = new SceneController();
        //     try {
        //         sc.switchTelaAdmin(e);
        //     } 
        //     catch (IOException e1) {
        //         e1.printStackTrace();
        //     }
        // });
    }

    private void puxaModal(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(pnModal);
        slide.setByX(-200);
        slide.play();
        pnModal.toFront();
        new animatefx.animation.ZoomIn(pnModal).setSpeed(1.4).play();;
    }

    private void voltaModal(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(pnModal);  
        slide.setByX(200);
        slide.play();
        pnModal.toFront();
        new animatefx.animation.ZoomOut(pnModal).setSpeed(1.4).play();;
    }

    private void preLoadDados(){
        if(Main.usuImg != null){
            Image usuImage = new Image(Main.usuImg, false);
            circleUsu.setFill(new ImagePattern(usuImage));
        }
        else{
            Image usuImage = new Image("/images/user.png");
            circleUsu.setFill(new ImagePattern(usuImage));
        }
        //Faz nome aparecer ao lado da foto do usuário
        lbNomeUsuario.setText(Main.nomeUsuario);
    }

}
