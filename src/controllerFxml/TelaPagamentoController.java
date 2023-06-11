package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.ControlCarrinho;
import controller.ControlPagamento;
import dao.DaoPagamento;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Pagamento;


public class TelaPagamentoController implements Initializable{

    private double x = 0, y = 0;
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

    //Dados user
    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;

    //INFORMACOES PRINCIPAIS DA TELA
    @FXML
    private Label precoTotal;

    @FXML
    private TextField cpf;

    @FXML
    private TextField numCartao;

    @FXML
    private TextField cvv;

    @FXML
    private Button btnFinalizarCompra;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControlPagamento cpg = new ControlPagamento();

        precoTotal.setText(Main.precoTotalCarrinho.toString());

        preLoadDadosUsuario();
        
        barra.setOnMousePressed(mouseEvent ->{
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        barra.setOnMouseDragged(mouseEvent ->{
            SceneController.stage.setX(mouseEvent.getScreenX() - x);
            SceneController.stage.setY(mouseEvent.getScreenY() - y);
        });

        imgLupa.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                Main.nomeJogoAux = tfPesquisa.getText();
                sc.switchTelaBusca(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaCarrinho(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        
        btnFechar.setOnMouseClicked((MouseEvent e)->{
            System.exit(1); 
        });
        
        imgCarrinho.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaCarrinho(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        // circleUsu.setOnMouseClicked((MouseEvent e)->{
        //     if(permite == true){
        //         puxaModal();
        //         permite = false;
        //     }
        //     else{
        //         voltaModal();
        //         permite = true;
        //     }
            
        // });
        
        btnFinalizarCompra.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            ControlCarrinho cc = new ControlCarrinho();
            DaoPagamento dp = new DaoPagamento();
            Pagamento pgt = new Pagamento();

            pgt.setCpf(cpf.getText());
            pgt.setCvv(Integer.parseInt(cpf.getText()));
            pgt.setNumCartao(numCartao.getText());
            pgt.setValor(Double.parseDouble(precoTotal.getText()));
            
            dp.insertDadosPagamento(pgt);
            ResultSet resultUltimoId = dp.ultimoIdInserido();
            
            try{
                cpg.finalizarCompra(resultUltimoId.getInt("idPagamento"));
                Main.precoTotalCarrinho = 0.0;
                precoTotal.setText("0");
                cc.limparCarrinho();
                sc.switchTelaBiblioteca(e);
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            catch(IOException e1){
                e1.printStackTrace();
            }

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
