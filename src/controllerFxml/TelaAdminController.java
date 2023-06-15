package controllerFxml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControlJogo;
import controller.ControlKey;
import controller.ControlUsuario;
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
import model.Jogo;

public class TelaAdminController implements Initializable{

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
    
    @FXML
    private TextField tfPesquisa;

    @FXML
    private ImageView imgLupa;

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

    //parte referte a exclusao de usuarios do sistema
    @FXML
    private TextField tfIdUsuario;

    @FXML
    private ImageView imgLupaUsu;

    @FXML
    private ImageView imgMonke;

    //@FXML
    //private Circle circleUsu2;

    //@FXML
    //private Label lbNomeUsuario2;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        Helper.preLoadComum(barra, btnVoltar, btnMinimizar, btnFechar, lbNomeUsuario, circleUsu, imgLupa, imgCarrinho, btnTelaConta, btnTelaBiblioteca, 
                            btnTelaDesejo, btnTelaAdmin, tfPesquisa, pnModal, linha4);  
 
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
        
        imgLupaUsu.setOnMouseClicked((MouseEvent e) -> {
            ControlUsuario cu = new ControlUsuario();
            cu.findByIdUsu(Integer.parseInt(tfIdUsuario.getText()));
        });

        imgMonke.setOnMouseClicked((MouseEvent e)->{
            ControlUsuario cu = new ControlUsuario();
            cu.deletarUsuario(Integer.parseInt(tfIdUsuario.getText()));
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

    // private void camposInv(){
    //     imgMonke.setVisible(false);
    //     circleUsu2.setVisible(false);
    //     lbNomeUsuario2.setVisible(false);
    // }

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
