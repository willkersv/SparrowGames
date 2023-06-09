package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ControlComentario;
import controller.ControlJogo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import model.Comentario;

public class TelaJogoController implements Initializable {

    private double x = 0, y = 0;

    @FXML
    private Pane barra;
    
    @FXML
    private ImageView btnVoltar;
    
    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;

    //Campos do jogo
    @FXML
    private ImageView imgJogo;

    @FXML
    private Label nomeJogo;

    @FXML
    private Label descJogo;

    @FXML
    private Label desenvolvedora;

    @FXML
    private Label preco;

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
        
        barra.setOnMousePressed(mouseEvent ->{
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        barra.setOnMouseDragged(mouseEvent ->{
            SceneController.stage.setX(mouseEvent.getScreenX() - x);
            SceneController.stage.setY(mouseEvent.getScreenY() - y);
        });

        preLoadDadosUsuario();
        infoJogo();

        btnConfirmar.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                cc.addComentario(tfComentario.getText());
                sc.switchTelaJogo(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
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

    private void infoJogo(){
        
        ControlJogo cj = new ControlJogo();
        nomeJogo.setText(cj.consultaJogo(Main.idJogoAux).getNomeJogo());
        descJogo.setText(cj.consultaJogo(Main.idJogoAux).getDescricao());
        desenvolvedora.setText(cj.consultaJogo(Main.idJogoAux).getDesenvolvedora());
        preco.setText(cj.consultaJogo(Main.idJogoAux).getPrecoJogo().toString());

        Image image = new Image(cj.consultaJogo(Main.idJogoAux).getImgJogo());
        System.out.println(cj.consultaJogo(Main.idJogoAux).getImgJogo());
        imgJogo.setImage(image);
        imgJogo.setPreserveRatio(true);
        imgJogo.setSmooth(true); 
    }

    private void preLoadDadosUsuario(){
        if(Main.usuImg != null){
            Image usuImage = new Image(Main.usuImg, false);
            circleUsu.setFill(new ImagePattern(usuImage));
            circleUsuComent.setFill(new ImagePattern(usuImage));
        }
        else{
            Image usuImage = new Image("/images/sparrow games.png");
            circleUsu.setFill(new ImagePattern(usuImage));
            circleUsuComent.setFill(new ImagePattern(usuImage));
        }
        //Faz nome aparecer ao lado da foto do usuário
        lbNomeUsuario.setText(Main.nomeUsuario);
    }

}
