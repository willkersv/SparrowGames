package controllerFxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ControlCarrinho;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Carrinho;


public class TelaCarrinhoController implements Initializable{

    private double x = 0, y = 0;
    //Barra superior things
   @FXML
    private Pane barra;

    @FXML
    private ImageView btnFechar;

    @FXML
    private ImageView btnMinimizar;

    @FXML
    private TextField tfPesquisa;
    
    @FXML
    private ImageView imgLupa;

    @FXML
    private ImageView imgCarrinho;

    //Dados user
    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;


    //Layout para aparecer os jogos
    @FXML
    private VBox jogoLayout;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControlCarrinho cc = new ControlCarrinho();
        List<Carrinho> carrinho  = new ArrayList<>(cc.exibirJogosCarrinho());  

        for(int i =0; i<carrinho.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/modeloCarrinho.fxml"));
                HBox hBox = fxmlLoader.load();
                ModeloCarrinhoController mcc = fxmlLoader.getController();
                mcc.setData(carrinho.get(i));
                jogoLayout.getChildren().add(hBox);
                    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        preLoadDadosUsuario();
        
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
