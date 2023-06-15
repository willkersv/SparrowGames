package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ControlCarrinho;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Carrinho;

public class TelaCarrinhoController implements Initializable{

    //Barra superior things
    @FXML
    private Pane barra;

    @FXML
    private ImageView btnVoltar;

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

    //Dados user
    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;


    //Layout para aparecer os jogos
    @FXML
    private VBox jogoLayout;

    //Parte inferior
    @FXML
    private Label precoTotal;

    @FXML
    private Button btnFinalizarCompra;

    private Double preco = 0.0;
    
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
                preco += carrinho.get(i).getPrecoJogo();
                    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        precoTotal.setText(preco.toString());

        Helper.preLoadComum(barra, btnVoltar, btnMinimizar, btnFechar, lbNomeUsuario, circleUsu, imgLupa, imgCarrinho, btnTelaConta, btnTelaBiblioteca, 
                            btnTelaDesejo, btnTelaAdmin, tfPesquisa, pnModal, linha4); 
        
        btnFinalizarCompra.setOnMouseClicked((MouseEvent e)->{ //VERIFICAR QUANTIDADE E DISPONIBILIDADE DAS KEYS DO INDIVIDUO AQUI!!!!!!
            SceneController sc = new SceneController();
            try{
                Main.precoTotalCarrinho = preco;
                sc.switchTelaPagamento(e);
            }catch(IOException e1){
                e1.printStackTrace();
            }
        });
    } 
    
}
