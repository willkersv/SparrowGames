package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.print.attribute.standard.PrinterIsAcceptingJobs;

import controller.ControlComentario;
import controller.ControlJogo;
import controller.ControlUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    //public static int idJogo;
    private double x = 0, y = 0;

    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;
    
    @FXML
    private Pane barra;

    //Campos do jogo
    @FXML
    private ImageView imgJogo;

    @FXML
    private Label labelNomeJogo;

    @FXML
    private Label descJogo;

    @FXML
    private Label desenvolvedora;

    @FXML
    private Label preco;

    //V-box dos comentario
    @FXML
    private VBox comentarioLayout;
    
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
            //NAO APAGAR ESSA PORRA KKKK
                //((ModeloComentController) fxmlLoader.getController()).getNomeUsuario().setText(comentarioList.get(i).getNomeUsuario());
                //((ModeloComentController) fxmlLoader.getController()).getLbComentario().setText(comentarioList.get(i).getComentario());
                //Image usuImage = new Image(comentarioList.get(i).getImgUsuario()); 
                //((ModeloComentController) fxmlLoader.getController()).getFotoComentario().setFill(new ImagePattern(usuImage));
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

        preLoadDados();
        infoJogo();
        
    }
    

    private void infoJogo(){
        
        ControlJogo cj = new ControlJogo();
        labelNomeJogo.setText(cj.consultaJogo(Main.idJogoAux).getNomeJogo());
        descJogo.setText(cj.consultaJogo(Main.idJogoAux).getDescricao());
        desenvolvedora.setText(cj.consultaJogo(Main.idJogoAux).getDesenvolvedora());
        preco.setText(cj.consultaJogo(Main.idJogoAux).getPrecoJogo().toString());

        Image image = new Image(cj.consultaJogo(Main.idJogoAux).getImgJogo());
        System.out.println(cj.consultaJogo(Main.idJogoAux).getImgJogo());
        imgJogo.setImage(image);
        imgJogo.setPreserveRatio(true);
        imgJogo.setSmooth(true); 
    }

    private void preLoadDados(){
        if(Main.usuImg != null){
            Image usuImage = new Image(Main.usuImg, false);
            circleUsu.setFill(new ImagePattern(usuImage));
        }
        else{
            Image usuImage = new Image("/images/user.png");
            circleUsu.setFill(new ImagePattern(usuImage));
        }
        //Faz nome aparecer ao lado da foto do usuário
        lbNomeUsuario.setText(Main.nomeUsuario);
    }
}
