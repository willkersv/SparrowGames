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

   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        btnSair1.setOnMouseClicked((MouseEvent e)->{
            sair();
        });

        btnSair2.setOnMouseClicked((MouseEvent e)->{
            sair();
        });

        circleUsu.setOnMouseClicked((MouseEvent e)->{
            selecionaFoto();
        });

        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            voltar();
            
        });
    }

    public void selecionaFoto(){
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new ExtensionFilter("Imagens","*.png", "*.jpg", "*.jpeg", "*.gif")); 
        File file = f.showOpenDialog(new Stage());
        //se der erro no notebook, colcoar ali em baixo o "file:///"+
        if(file != null){
            
            Image Img = new Image(file.getAbsolutePath(), false);
            circleUsu.setFill(new ImagePattern(Img));
            caminhoImg = file.getAbsolutePath();
        }}

    @FXML
    protected void btCadastrar(ActionEvent e) throws IOException{
        Usuario us = new Usuario();
        ControlUsuario cu = new ControlUsuario();
        boolean confirma;
        if(tfSenhaCad1.getText().equals(tfSenhaCad2.getText())){
            us.setNomeUsuario(tfNomeCad.getText());
            us.setEmailUsuario(tfEmailCad.getText());
            us.setSenhaUsuario(tfSenhaCad1.getText());
            us.setImgUsuario(caminhoImg);
            confirma = cu.cadastrarUsuario(us);
            if(confirma == true){
                voltar();
            }
            else{
                System.out.println("f total");
            } 
        }
        else{
            System.out.println("Senhas diferentes, fera");
        }
    }
    
    protected void voltar(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(pnCadastro);
        slide.setToY(700);
        slide.play();
    }

    @FXML
    protected void login(ActionEvent e) throws IOException{
        
        Usuario us = new Usuario();
        ControlUsuario cu = new ControlUsuario();
        us.setEmailUsuario(tfEmail.getText());
        us.setSenhaUsuario(tfSenha.getText());
        System.out.println("------------------------\nBOTAO LOGIN AINDA FUNCIONA");
        cu.consultarLogin(us);
        SceneController sc = new SceneController();
        if(Main.emailIdent == null){ 
            String css = this.getClass().getResource("/css/invalido.css").toExternalForm();
            lbIncorreto.setVisible(true);
            Main.mainScene.getStylesheets().add(css);
            tfSenha.setText("");
        }else{
            sc.switchTelaInicial(e);
        }

    }

    @FXML
    private void sair(){
        //usar o System.exit para fechar tudo :)
        System.exit(1);
        //Stage stage = (Stage) btnFechar.getScene().getWindow();
        //stage.close();
    }

    @FXML
    private void hyCadastro(ActionEvent e) throws IOException{
        
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(pnCadastro);
        slide.setToY(-700);
        slide.play();
        pnCadastro.toFront();
        Image usuImage = new Image("/images/sparrow games.png", false);
        circleUsu.setFill(new ImagePattern(usuImage));

    }


    

}

