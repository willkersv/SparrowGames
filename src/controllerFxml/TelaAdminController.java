package controllerFxml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControlJogo;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Jogo;

public class TelaAdminController implements Initializable{

    private double x = 0, y = 0;

    @FXML
    private Pane pnModal;

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

    //parte referte a exlusao de usuarios do sistema
    @FXML
    private TextField tfIdUsuario;

    
    
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

        preLoadDadosUsuario();

        imgJogo.setOnMouseClicked((MouseEvent e)->{
           selecionaFoto();
        });

        btnCamera.setOnMouseClicked((MouseEvent e)->{
           selecionaFoto();
        });

        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaInicial(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
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
            System.out.println("f total ao cadastrar o jogo");
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
    

    //preload
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

    private void limpaCamposJogo() {
        tfNomeJogo.setText("");
        tfPrecoJogo.setText("");
        tfDesenvolvedora.setText("");
        tfDescricao.setText(""); 
    
    }

    
}
