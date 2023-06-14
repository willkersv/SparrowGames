package controllerFxml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControlUsuario;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TelaContaController implements Initializable{
    
    private double x = 0, y = 0;

    //BARRA SUPERIOR
    @FXML
    private Pane barra;
    
    @FXML
    private ImageView btnVoltar;

    @FXML
    private ImageView btnFechar;

    @FXML
    private ImageView btnMinimizar;
    
    @FXML
    private ImageView imgCarrinho;

    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;

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

    //Campos para edicao
    @FXML
    private Circle circleUsu2;
    
    private String caminhoImg;

    @FXML
    private Label lbNovoNome;

    @FXML
    private TextField tfNovoNome;

    @FXML
    private ImageView imgUpdate;


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

        circleUsu2.setOnMouseClicked((MouseEvent e)->{
            selecionaFoto();
            //lbConfirmaFoto.setVisible(true)
        });
        
        imgUpdate.setOnMouseClicked((MouseEvent e)->{
            ControlUsuario cu = new ControlUsuario();
            if(tfNovoNome.getText() != "" || tfNovoNome.getText() != null){
                cu.attNome(tfNovoNome.getText());
                Main.nomeUsuario = tfNovoNome.getText();
                SceneController sc = new SceneController();
                try {
                    sc.switchTelaConta(e);
                } catch (IOException e1) {

                    e1.printStackTrace();
                }
            }
             
        });
    }

    private void selecionaFoto(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Imagens","*.png", "*.jpg", "*.jpeg", "*.gif")); 
        File file = fc.showOpenDialog(new Stage());
        //se der erro no notebook, colcoar ali em baixo o "file:///"+
        if(file != null){
            
            Image Img = new Image(file.getAbsolutePath(), false);
            circleUsu2.setFill(new ImagePattern(Img));
            caminhoImg = file.getAbsolutePath();
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
        circleUsu2.setFill(new ImagePattern(usuImage));     
        //Faz nome aparecer ao lado da foto do usuário
        lbNomeUsuario.setText(Main.nomeUsuario);
        lbNovoNome.setText(Main.nomeUsuario);
    }
    
}
