package controllerFxml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControlUsuario;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Usuario;



public class TelaLoginController implements Initializable{

    public String caminhoImg;
    
    @FXML
    private ImageView btnSair1;
    
    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfSenha;
    
    @FXML
    private Hyperlink hySenha;
    
    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink hylCadastro;

    @FXML
    private Pane pnCadastro;

    @FXML
    private Pane pnLogin;

    @FXML
    private Label lbIncorreto;
    
    //Tela cadastro
    @FXML
    private ImageView btnVoltar;

    @FXML
    private ImageView btnSair2;
    
    @FXML
    private Circle circleUsu;

    @FXML
    private TextField tfNomeCad; 
    
    @FXML
    private TextField tfEmailCad;

    @FXML
    private PasswordField tfSenhaCad1;

    @FXML
    private PasswordField tfSenhaCad2;
    
    @FXML
    private Button btnCadastrar;

    //Tela de recuperar senha
    @FXML
    private Pane pnRecSenha;

    @FXML
    private ImageView btnSair3;

    @FXML
    private ImageView btnVoltarRecSenha;

    @FXML
    private TextField tfEmailRec;

    @FXML
    private TextField tfCodRec;

    //Tela de Redigitar senha
    @FXML
    private Pane pnRedSenha;

    @FXML
    private ImageView btnVoltar3;

    @FXML
    private Button btnRecSenha;
    
    @FXML
    private PasswordField tfNovaSenha1;

    @FXML
    private PasswordField tfNovaSenha2;
    
    @FXML
    private Pane barraCod;

    @FXML
    private Label labCodSenha;

    private int permite;

    private String cssSenha = "-fx-border-color:#ff0000 !important; -fx-border-width: 0px 0px 3px 0px !important; -fx-background-color: transparent !important; -fx-text-fill:  #D7D7D7 !important;";
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        btnSair1.setOnMouseClicked((MouseEvent e)->{
            sair();
        });

        btnSair2.setOnMouseClicked((MouseEvent e)->{
            sair();
        });

        btnSair3.setOnMouseClicked((MouseEvent e)->{
            sair();
        });

        circleUsu.setOnMouseClicked((MouseEvent e)->{
            selecionaFoto();
        });

        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            voltarCodSenha();
            voltarCad();
            
        });

        btnVoltarRecSenha.setOnMouseClicked((MouseEvent e)->{
            voltarRecSenha();
            
        });
        
        btnVoltar3.setOnMouseClicked((MouseEvent e)->{
            voltarRedSenha();
            
        });

        tfEmail.setOnMouseClicked((MouseEvent e)->{
            if(permite == 1){
                voltarCodSenha();
            }
            permite = 0;
            
        });

        btnLogin.setOnMouseClicked((MouseEvent e)->{
            try {
                login(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });
    }

    //Funções dos botões de mais importancia
    @FXML
    private void sair(){
        System.exit(1);
        //Stage stage = (Stage) btnFechar.getScene().getWindow();
        //stage.close();
    }
    
    @FXML
    private void login(MouseEvent e) throws IOException{
        
        ControlUsuario cu = new ControlUsuario();
        System.out.println("------------------------\nBOTAO LOGIN AINDA FUNCIONA");
        boolean confirma = cu.consultarLogin(tfEmail.getText(), tfSenha.getText());
        if(confirma == false){ 
            String css = this.getClass().getResource("/css/invalido.css").toExternalForm();
            lbIncorreto.setVisible(true);
            Main.mainScene.getStylesheets().add(css);
            tfSenha.setText("");
        }else{
            SceneController sc = new SceneController();
            sc.switchTelaInicial(e);
        }

    }
    
    @FXML
    private void btCadastrar(ActionEvent e) throws IOException{
        Usuario us = new Usuario();
        ControlUsuario cu = new ControlUsuario();
        boolean confirma;
        if(tfEmailCad.getText() == "" || tfNomeCad.getText() == "" || tfSenhaCad1.getText()== "" || tfSenhaCad2.getText() == ""){
            System.out.println("Preenche tudo ai");
        }else{
            if(tfSenhaCad1.getText().equals(tfSenhaCad2.getText())){
                us.setNomeUsuario(tfNomeCad.getText());
                us.setEmailUsuario(tfEmailCad.getText());
                us.setSenhaUsuario(tfSenhaCad1.getText());
            if(caminhoImg == null){
                caminhoImg = "/images/sparrow games.png";
            }
            us.setImgUsuario(caminhoImg);
            String tempCod = cu.codRecuperarSenha();
            labCodSenha.setText(tempCod);
            us.setCodRec(tempCod);
            confirma = cu.cadastrarUsuario(us);
            if(confirma == true){
                limpaCamposCad();
                voltarCad();
                sobeCodSenha();
                permite = 1;
            }
            else{
                
                System.out.println("f total");
            } 
        }
        else{
            tfSenhaCad1.setStyle(cssSenha);
            tfSenhaCad2.setStyle(cssSenha);
            System.out.println("Senhas diferentes, fera");
        }
            
        }
        
        
    }

    private void selecionaFoto(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Imagens","*.png", "*.jpg", "*.jpeg", "*.gif")); 
        File file = fc.showOpenDialog(new Stage());
        //se der erro no notebook, colcoar ali em baixo o "file:///"+
        if(file != null){
            
            Image Img = new Image(file.getAbsolutePath(), false);
            circleUsu.setFill(new ImagePattern(Img));
            caminhoImg = file.getAbsolutePath();
        }}

    @FXML
    void btRecSenha(){
        ControlUsuario cu = new ControlUsuario();
        System.out.println("------------------------\nBOTAO REC AINDA FUNCIONA");
        if(cu.consultaCodRec(tfEmailRec.getText(), tfCodRec.getText()).equals(tfCodRec.getText())){
            Main.emailIdent= tfEmailRec.getText();
            limpaCamposRec();
            voltarRecSenha();
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(1));
            slide.setNode(pnRedSenha);
            slide.setToY(700);
            slide.play();
        }
        else{
            tfCodRec.setStyle(cssSenha);
            tfEmailRec.setStyle(cssSenha);
        }
    }
    
    @FXML
    private void btRedSenha(){
        ControlUsuario cu = new ControlUsuario();
        System.out.println("------------------------\nBOTAO RED AINDA FUNCIONA");
        if((tfNovaSenha1.getText()).equals(tfNovaSenha2.getText())){
            String temp = cu.codRecuperarSenha();
            cu.alterarSenhaUsuario(tfNovaSenha1.getText(), temp);
            labCodSenha.setText(temp);
            limpaCamposRed();
            voltarRedSenha();
            sobeCodSenha();
            permite = 1;
        }
        else{
            tfNovaSenha1.setStyle(cssSenha);
            tfNovaSenha2.setStyle(cssSenha);
        }
    }


    @FXML
    private void hyCadastro(ActionEvent e) throws IOException{
        
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(pnCadastro);
        slide.setToY(-810);
        slide.play();
        pnCadastro.toFront();
        Image usuImage = new Image("/images/sparrow games.png", false);
        circleUsu.setFill(new ImagePattern(usuImage));

    }

    @FXML
    void hySenha(ActionEvent event) {
        
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(pnRecSenha);
        slide.setToX(-700);
        slide.play();
        pnCadastro.toFront();

    }

    //Funções para voltar de tela em tela
    protected void voltarCad(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(pnCadastro);
        slide.setToY(700);
        slide.play();
    }
    
    @FXML
    void voltarRecSenha() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(pnRecSenha);
        slide.setToX(700);
        slide.play();
    }
    
    @FXML
    void voltarRedSenha(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(2));
        slide.setNode(pnRedSenha);
        slide.setToY(-700);
        slide.play();
        voltarCodSenha();
    }

    @FXML
    void voltarCodSenha(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(barraCod);
        slide.setToY(90);
        slide.play();
    }
    
    //Funções para subir as telas
    @FXML
    void sobeCodSenha(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(barraCod);
        slide.setToY(-90);
        slide.play();
        barraCod.toFront();
    }

    //Funções pra limpar campos das telas
    private void limpaCamposCad(){
        tfNomeCad.setText("");
        tfEmailCad.setText("");
        tfSenhaCad1.setText("");
        tfSenhaCad2.setText("");
    }

    private void limpaCamposRec(){
        tfEmailRec.setText("");
        tfCodRec.setText("");
    }
    
    private void limpaCamposRed(){
        tfSenhaCad1.setText("");
        tfSenhaCad2.setText("");
    }

    

}

