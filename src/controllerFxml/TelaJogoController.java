package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ControlCarrinho;
import controller.ControlComentario;
import controller.ControlDesejo;
import controller.ControlJogo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.StageStyle;
import model.Comentario;

public class TelaJogoController implements Initializable {

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

    @FXML
    private TextField tfPesquisa;
    
    @FXML
    private ImageView imgLupa;

    //Modal things
    
    //Variavel para o modalzinho
    
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

    //Campos do jogo
    @FXML
    private Rectangle imgJogo;

    @FXML
    private Label nomeJogo;

    @FXML
    private Label descJogo;

    @FXML
    private Label desenvolvedora;

    @FXML
    private Label preco;

    @FXML
    private ImageView imgHeart;

    @FXML
    private ImageView imgHeartFull;

    @FXML
    private Button btnAddCarrinho;

    @FXML
    private TextField tfNovoPreco;

    @FXML  
    private ImageView imgConfirmaPreco;

    //V-box dos comentario
    @FXML
    private VBox comentarioLayout;

    //Comentario
    @FXML
    private Circle circleUsuComent;
    
    @FXML
    private TextField tfComentario;

    @FXML
    private ImageView btnConfirmar;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControlComentario cc = new ControlComentario();
        List<Comentario> comentarioList  = new ArrayList<>(cc.exibirComentarios());  

        for(int i =0; i<comentarioList.size(); i++){
            
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/modeloComent.fxml"));
                HBox hBox = fxmlLoader.load();
                ModeloComentController mcd = fxmlLoader.getController();
                mcd.setData(comentarioList.get(i));
                comentarioLayout.getChildren().add(hBox);
                    
            } catch (Exception e) {
            e.printStackTrace();
            }
        }

        Helper.preLoadComum(barra, btnVoltar, btnMinimizar, btnFechar, lbNomeUsuario, circleUsu, imgLupa, imgCarrinho, btnTelaConta, btnTelaBiblioteca, 
                            btnTelaDesejo, btnTelaAdmin, tfPesquisa, pnModal, linha4);
        preLoadDados();
        
        infoJogo();
        
        verfificaDesejo();

        //parte referente ao jogo
        imgHeart.setOnMouseClicked((MouseEvent e)->{
            imgHeartFull.setVisible(true); 
            new animatefx.animation.ZoomIn(imgHeartFull).setSpeed(1.4).play();
            imgHeartFull.toFront();
            ControlDesejo cd = new ControlDesejo();
            cd.addDesejo();
        });
        
        imgHeartFull.setOnMouseClicked((MouseEvent e)->{
            imgHeart.setVisible(true);
            new animatefx.animation.ZoomOut(imgHeartFull).setSpeed(1.4).play();
            imgHeart.toFront();
            ControlDesejo cd = new ControlDesejo();
            cd.excluiDesejo();
        });

        
        // btnEditarJogo.setOnMouseClicked((MouseEvent e)->{
        //     ControlJogo cj = new ControlJogo();
        //     cj.attPreco(tfNovoPreco.getText());
        // SceneController sc = new SceneController();
        //     try {
        //         sc.switchTelaJogo(e);
        //     } catch (IOException e1) {

        //         e1.printStackTrace();
        //     }
        // });

        btnAddCarrinho.setOnMouseClicked((MouseEvent e)->{
            ControlCarrinho ccr = new ControlCarrinho();
            if(ccr.addJogoCarrinho() == false){
                Alert a = new Alert(AlertType.CONFIRMATION);
                a.initStyle(StageStyle.UNDECORATED);
                a.setContentText("O jogo já foi adicionado ao seu carrinho");
                a.show();
            }else{
                Alert a = new Alert(AlertType.CONFIRMATION);
                a.initStyle(StageStyle.UNDECORATED);
                a.setContentText("Jogo adicionado ao carrinho");
                a.show();
            }

        });
        
        //Referente ao comentario
        btnConfirmar.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                cc.addComentario(tfComentario.getText());
                sc.switchTelaJogo(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });
    }

    private void infoJogo(){
        
        ControlJogo cj = new ControlJogo();
        nomeJogo.setText(cj.consultaJogo(Main.idJogoAux).getNomeJogo());
        descJogo.setText(cj.consultaJogo(Main.idJogoAux).getDescricao());
        desenvolvedora.setText(cj.consultaJogo(Main.idJogoAux).getDesenvolvedora());
        preco.setText(cj.consultaJogo(Main.idJogoAux).getPrecoJogo().toString());

        Image image = new Image(cj.consultaJogo(Main.idJogoAux).getImgJogo());
        System.out.println(cj.consultaJogo(Main.idJogoAux).getImgJogo());
        imgJogo.setFill(new ImagePattern(image));
        imgJogo.setSmooth(true); 
    }
    
    private void verfificaDesejo(){
        ControlDesejo cd = new ControlDesejo();
        boolean desejo = cd.consultaDesejo();
        if(desejo == true){
            imgHeartFull.setVisible(true);
            imgHeartFull.toFront();
        }
    }
    
    private void preLoadDados(){
        //Imagem do Usuario no comentario
        Image usuImage = new Image(Main.usuImg, false);
        circleUsuComent.setFill(new ImagePattern(usuImage));
        if(Main.verAdmin == false){
            imgConfirmaPreco.setVisible(false);
            tfNovoPreco.setVisible(false);
            btnTelaAdmin.setVisible(false);
            linha4.setVisible(false);
            pnModal.setMaxHeight(124);
        }
    }

    

}
