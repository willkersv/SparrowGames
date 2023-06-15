package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class TelaInicialController implements Initializable{
    

    //BARRA SUPERIOR THINGS
    @FXML
    private Pane barra;

    @FXML
    private ImageView btnVoltar;
    
    @FXML
    private ImageView btnFechar;

    @FXML
    private ImageView btnMinimizar;

    @FXML
    private TextField tfPesquisa;
    
    @FXML
    private ImageView imgLupa;

    @FXML
    private ImageView imgCarrinho;
    
    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;
    
    //Modal things
    
    @FXML
    private Pane pnModal;

    @FXML
    private Button btnTelaConta;
    
    @FXML
    private Button btnTelaBiblioteca;

    @FXML
    private Button btnTelaDesejo;

    @FXML
    private Button btnTelaAdmin;

    @FXML
    private Line linha4;

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
        Main.idJogoAux = 2;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }

    private void TelaTW(MouseEvent e) throws IOException{
        Main.idJogoAux = 3;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e);
    }

    private void TelaDS(MouseEvent e) throws IOException{
        Main.idJogoAux = 4;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }

    private void TelaBB(MouseEvent e) throws IOException{
        Main.idJogoAux = 5;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }

    private void TelaFS(MouseEvent e) throws IOException{
        Main.idJogoAux = 6;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }

    private void TelaMK(MouseEvent e) throws IOException{
        Main.idJogoAux = 7;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }

    private void TelaWD(MouseEvent e) throws IOException{
        Main.idJogoAux = 8;
        SceneController sc = new SceneController();
        sc.switchTelaJogo(e); 
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) { 

        Helper.preLoadComum(barra, btnVoltar, btnMinimizar, btnFechar, lbNomeUsuario, circleUsu, imgLupa, imgCarrinho, btnTelaConta, btnTelaBiblioteca, 
                            btnTelaDesejo, btnTelaAdmin, tfPesquisa, pnModal, linha4);

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
    }
    
}
