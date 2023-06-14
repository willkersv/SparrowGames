package controllerFxml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControlJogo;
import controller.ControlKey;
import controller.ControlUsuario;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Jogo;

public class TelaAdminController implements Initializable{

    private double x = 0, y = 0;

    //Barra superior
    @FXML
    private Pane barra;

    @FXML
    private ImageView btnFechar;

    @FXML
    private ImageView btnMinimizar; 

    @FXML
    private ImageView btnVoltar;

    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;

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

    //Parte referente ao jogo
    private String caminhoImgJogo;
    
    @FXML
    private Rectangle imgJogo;

    @FXML
    private Rectangle btnCamera;

    @FXML
    private TextField tfNomeJogo;

    @FXML
    private TextField tfPrecoJogo;

    @FXML
    private TextField tfDesenvolvedora;

    @FXML
    private TextField tfDescricao;

    @FXML
    private Button btnAdicionarJogo;

    //Parte refente a adicao de keys
    @FXML
    private TextField tfIdJogo;

    @FXML
    private TextField tfSerialKey;

    @FXML
    private Button btnKey;

    //parte referte a exlusao de usuarios do sistema
    @FXML
    private TextField tfIdUsuario;

    @FXML
    private ImageView imgLupaUsu;

    @FXML
    private ImageView imgLixeira;

    @FXML
    private Circle circleUsu2;

    @FXML
    private Label lbNomeUsuario2;

    
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
        
        camposInv();
        preLoadDadosUsuario();

        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaInicial(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
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
        
        
        imgJogo.setOnMouseClicked((MouseEvent e)->{
           selecionaFoto();
        });

        btnCamera.setOnMouseClicked((MouseEvent e)->{
           selecionaFoto();
        });
        
        //Acoes da key
        btnKey.setOnMouseClicked((MouseEvent e)->{
            ControlKey ck = new ControlKey();
            boolean confirma = ck.addKey(Integer.parseInt(tfIdJogo.getText()), tfSerialKey.getText());
            if(confirma == true){
                limpaCamposKey();
                Alert a = new Alert(AlertType.CONFIRMATION);
                a.initStyle(StageStyle.UNDECORATED);
                a.setContentText("Key inserida com sucesso no banco");
                a.show();
            }
           else{
                Alert a = new Alert(AlertType.WARNING);
                a.initStyle(StageStyle.UNDECORATED);
                a.setContentText("Erro ao inserir a key");
                a.show();
           }
        });
        
        btnFechar.setOnMouseClicked((MouseEvent e)->{
            System.exit(1); 
        });

        imgLupaUsu.setOnMouseClicked((MouseEvent e) -> {
            ControlUsuario cu = new ControlUsuario();
            cu.findByIdUsu(Integer.parseInt(tfIdUsuario.getText()));
        });
    }

    //Funcoes para adicionar o jogo
    @FXML
    protected void btAdicionar(ActionEvent e) throws IOException{
        Jogo jg = new Jogo();
        ControlJogo cj = new ControlJogo();
        boolean confirma;
        jg.setNomeJogo(tfNomeJogo.getText());
        jg.setPrecoJogo(Double.parseDouble(tfPrecoJogo.getText()));
        jg.setDesenvolvedora(tfDesenvolvedora.getText());
        jg.setDescricao(tfDescricao.getText());
        jg.setImgJogo(caminhoImgJogo);
        confirma = cj.insereJogo(jg);
        if(confirma == true){
            limpaCamposJogo();
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.initStyle(StageStyle.UNDECORATED);
            a.setContentText("O jogo foi inserido com sucesso");
            a.show();
        }
        else{    
            Alert a = new Alert(AlertType.WARNING);
            a.initStyle(StageStyle.UNDECORATED);
            a.setContentText("Erro ao adiconar o jogo"); 
            a.show();
        } 
    
    }

    private void selecionaFoto(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Imagens","*.png", "*.jpg", "*.jpeg", "*.gif")); 
        File file = fc.showOpenDialog(new Stage());
        //se der erro no notebook, colcoar ali em baixo o "file:///"+
        if(file != null){
            Image Img = new Image(file.getAbsolutePath(), false);
            imgJogo.setFill(new ImagePattern(Img));
            caminhoImgJogo = file.getAbsolutePath();
        }}
    

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

        Image usuImage = new Image(Main.usuImg, false);
        circleUsu.setFill(new ImagePattern(usuImage));
        //Faz nome aparecer ao lado da foto do usuário
        lbNomeUsuario.setText(Main.nomeUsuario);
    }

    private void camposInv(){
        imgLixeira.setVisible(false);
        circleUsu2.setVisible(false);
        lbNomeUsuario2.setVisible(false);
    }

    private void limpaCamposJogo() {
        tfNomeJogo.setText("");
        tfPrecoJogo.setText("");
        tfDesenvolvedora.setText("");
        tfDescricao.setText(""); 
    }

    private void limpaCamposKey() {
        tfIdJogo.setText("");
        tfSerialKey.setText("");
    }

    
}
