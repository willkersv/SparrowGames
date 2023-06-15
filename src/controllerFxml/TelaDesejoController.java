package controllerFxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ControlDesejo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Desejo;

public class TelaDesejoController implements Initializable{
  
    //BARRA SUPERIOR THINGS
    @FXML
    private Pane barra;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private ImageView btnFechar;

    @FXML
    private ImageView btnMinimizar;
    
    @FXML
    private ImageView imgLupa;

    @FXML
    private ImageView imgCarrinho;

    @FXML
    private TextField tfPesquisa;

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

    //////////////////////////////
    @FXML
    private VBox jogoLayout;

    @FXML
    private Label lbNomeUsuario;

    @FXML
    private Circle circleUsu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControlDesejo cd = new ControlDesejo();
        List<Desejo> desejo  = new ArrayList<>(cd.exibirDesejo());  

        for(int i =0; i<desejo.size(); i++){
            
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/modeloDesejo.fxml"));
                HBox hBox = fxmlLoader.load();
                ModeloDesejoController mdc = fxmlLoader.getController();
                mdc.setData(desejo.get(i));
                jogoLayout.getChildren().add(hBox);
                    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Helper.preLoadComum(barra, btnVoltar, btnMinimizar, btnFechar, lbNomeUsuario, circleUsu, imgLupa, imgCarrinho, btnTelaConta, btnTelaBiblioteca, 
                            btnTelaDesejo, btnTelaAdmin, tfPesquisa, pnModal, linha4);    
    }

}
