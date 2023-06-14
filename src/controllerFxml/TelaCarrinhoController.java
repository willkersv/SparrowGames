package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ControlCarrinho;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.Carrinho;

public class TelaCarrinhoController implements Initializable{

    private double x = 0, y = 0;
    //Barra superior things
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

    //Modal things
    
    //Variavel para o modalzinho
    private boolean permite = true;
    
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

    //Dados user
    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;


    //Layout para aparecer os jogos
    @FXML
    private VBox jogoLayout;

    //Parte inferior
    @FXML
    private Label precoTotal;

    @FXML
    private Button btnFinalizarCompra;

    private Double preco = 0.0;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControlCarrinho cc = new ControlCarrinho();
        List<Carrinho> carrinho  = new ArrayList<>(cc.exibirJogosCarrinho());  

        for(int i =0; i<carrinho.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/modeloCarrinho.fxml"));
                HBox hBox = fxmlLoader.load();
                ModeloCarrinhoController mcc = fxmlLoader.getController();
                mcc.setData(carrinho.get(i));
                jogoLayout.getChildren().add(hBox);
                preco += carrinho.get(i).getPrecoJogo();
                    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        precoTotal.setText(preco.toString());
        
        preLoadDadosUsuario();
        
        barra.setOnMousePressed(mouseEvent ->{
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        barra.setOnMouseDragged(mouseEvent ->{
            SceneController.stage.setX(mouseEvent.getScreenX() - x);
            SceneController.stage.setY(mouseEvent.getScreenY() - y);
        });

        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaInicial(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        
        btnFechar.setOnMouseClicked((MouseEvent e)->{
            System.exit(1); 
        });

        //Botões do modal
        btnTelaConta.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaConta(e);
            } 
            catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        
        btnTelaBiblioteca.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaBiblioteca(e);
            } 
            catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        btnTelaDesejo.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaDesejo(e);
            } 
            catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        btnTelaAdmin.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaAdmin(e);
            } 
            catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        imgLupa.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                Main.nomeJogoAux = tfPesquisa.getText();
                sc.switchTelaBusca(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


        imgLupa.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                Main.nomeJogoAux = tfPesquisa.getText();
                sc.switchTelaBusca(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        
        imgCarrinho.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaCarrinho(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        // circleUsu.setOnMouseClicked((MouseEvent e)->{
        //     if(permite == true){
        //         puxaModal();
        //         permite = false;
        //     }
        //     else{
        //         voltaModal();
        //         permite = true;
        //     }
            
        // });
        
        barra.setOnMousePressed(mouseEvent ->{
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        barra.setOnMouseDragged(mouseEvent ->{
            SceneController.stage.setX(mouseEvent.getScreenX() - x);
            SceneController.stage.setY(mouseEvent.getScreenY() - y);
        });

        preLoadDadosUsuario();

        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaInicial(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });

        btnFechar.setOnMouseClicked((MouseEvent e)->{
            System.exit(1); 
        });
        
        
        btnFinalizarCompra.setOnMouseClicked((MouseEvent e)->{ //VERIFICAR QUANTIDADE E DISPONIBILIDADE DAS KEYS DO INDIVIDUO AQUI!!!!!!
            SceneController sc = new SceneController();
            try{
                Main.precoTotalCarrinho = preco;
                sc.switchTelaPagamento(e);
            }catch(IOException e1){
                e1.printStackTrace();
            }
        });
    }

    //Puxa/Volta o modal
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

    private void preLoadDadosUsuario(){
        if(Main.usuImg != null){
            Image usuImage = new Image(Main.usuImg, false);
            circleUsu.setFill(new ImagePattern(usuImage));
        }
        else{
            Image usuImage = new Image("/images/sparrow games.png");
            circleUsu.setFill(new ImagePattern(usuImage));
        }
        //Faz nome aparecer ao lado da foto do usuário
        lbNomeUsuario.setText(Main.nomeUsuario);
    }

    
    
}
