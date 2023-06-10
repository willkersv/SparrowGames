package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ControlJogo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Jogo;

public class TelaBuscaController implements Initializable{

    private double x = 0, y = 0;
    
    //BARRA SUPERIOR THINGS
    @FXML
    private Pane barra;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private ImageView btnFechar;

    @FXML
    private ImageView btnMinimizar;
   //////////////////////////////////
    @FXML
    private VBox jogoLayout;

    @FXML
    private Label lbNomeUsuario;

    @FXML
    private Circle circleUsu;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControlJogo cj = new ControlJogo();
        List<Jogo> jogoList  = new ArrayList<>(cj.exibirJogos());  

        for(int i =0; i<jogoList.size(); i++){
            
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/modeloBusca.fxml"));
                HBox hBox = fxmlLoader.load();
                ModeloBuscaController mbc = fxmlLoader.getController();
                mbc.setData(jogoList.get(i));
                jogoLayout.getChildren().add(hBox);
                    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        preLoadDadosUsuario();
        
        //Botoes do topo
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

        
        barra.setOnMousePressed(mouseEvent ->{
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        barra.setOnMouseDragged(mouseEvent ->{
            SceneController.stage.setX(mouseEvent.getScreenX() - x);
            SceneController.stage.setY(mouseEvent.getScreenY() - y);
        });
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
