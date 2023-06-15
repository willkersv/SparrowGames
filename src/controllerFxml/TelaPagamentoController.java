package controllerFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControlCarrinho;
import controller.ControlPagamento;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Pagamento;


public class TelaPagamentoController implements Initializable{

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

    //Dados user
    @FXML
    private Circle circleUsu;

    @FXML
    private Label lbNomeUsuario;

    //INFORMACOES PRINCIPAIS DA TELA
    @FXML
    private Rectangle imgCartao;

    @FXML
    private Label lbNumeroCartao;
    
    @FXML
    private Label lbCVV;
    
    @FXML
    private Label lbNomeCartao;
    
    @FXML
    private Label precoTotal;

    @FXML
    private TextField tfNomeCartao;
    
    @FXML
    private TextField tfCPF;

    @FXML
    private TextField tfNumeroCartao;

    @FXML
    private TextField tfCVV;

    @FXML
    private Button btnFinalizarCompra;

    //Painel obrigado
    @FXML
    private Pane pnObrigado;

    @FXML
    private ImageView imgFecharObrigado;

    int i = 0;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControlPagamento cpg = new ControlPagamento();

        precoTotal.setText(Main.precoTotalCarrinho.toString());
        
        preLoadDadosUsuario();
        
        Helper.preLoadComum(barra, btnVoltar, btnMinimizar, btnFechar, lbNomeUsuario, circleUsu, imgLupa, imgCarrinho, btnTelaConta, btnTelaBiblioteca, 
                            btnTelaDesejo, btnTelaAdmin, tfPesquisa, pnModal, linha4);

        tfNumeroCartao.setOnKeyTyped((KeyEvent e)->{
            
            if(tfNumeroCartao.getText().length() == 1){
                System.out.println(tfNumeroCartao.getText().substring(0, 1));
                int primNum = Integer.parseInt(tfNumeroCartao.getText().substring(0, 1));
                System.out.println(primNum);
                if(primNum == 5){
                    Image usuImage = new Image("/images/cartaoMaster.png", false);
                    imgCartao.setFill(new ImagePattern(usuImage));
                }
                else if(primNum == 4){
                    Image usuImage = new Image("/images/cartaoVisa.png", false);
                    imgCartao.setFill(new ImagePattern(usuImage));
                }
                else{
                    Image usuImage = new Image("/images/cartaoPadrao.png", false);
                    imgCartao.setFill(new ImagePattern(usuImage));  
                }
            }
            lbNumeroCartao.setText(tfNumeroCartao.getText());
            
        });

        tfCVV.setOnKeyTyped((KeyEvent e)->{
            lbCVV.setText(tfCVV.getText());
        });

        tfNomeCartao.setOnKeyTyped((KeyEvent e)->{
            String temp = tfNomeCartao.getText().toUpperCase();
            tfNomeCartao.setText("");
            tfNomeCartao.replaceText(0, i, temp);
            tfNomeCartao.selectForward();
            lbNomeCartao.setText(tfNomeCartao.getText().toUpperCase());
        });
        
        
        imgFecharObrigado.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                voltaObrigado();
                sc.switchTelaBiblioteca(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        
        btnFinalizarCompra.setOnMouseClicked((MouseEvent e)->{
            ControlCarrinho cc = new ControlCarrinho();
            Pagamento pgt = new Pagamento();

            //insere dados no tipo Pagamento
            pgt.setCpf(tfCPF.getText());
            pgt.setCvv(Integer.parseInt(tfCVV.getText()));
            pgt.setNumCartao(tfNumeroCartao.getText());
            pgt.setValor(Double.parseDouble(precoTotal.getText()));
            

            cpg.finalizarCompra(pgt);
            Main.precoTotalCarrinho = 0.0;
            precoTotal.setText("0");
            cc.limparCarrinho();
            sobeObrigado();

        });

        //Faz as limitacoes com o length do campo e se só pode numeros
        Helper.limitTextField(tfNumeroCartao, 16);
        Helper.onlyNumbers(tfNumeroCartao);
        
        Helper.limitTextField(tfCVV, 3);
        Helper.onlyNumbers(tfCVV);

        Helper.mascaraCPF(tfCPF);
        Helper.limitTextField(tfCPF, 14);
 
    }

    private void sobeObrigado(){
        System.out.println("entro aqui");
        TranslateTransition slide = new TranslateTransition();
        pnObrigado.toBack();
        slide.setDuration(Duration.seconds(0.01));
        slide.setNode(pnObrigado);
        slide.setToY(-582);
        slide.play();
        pnObrigado.toFront();
        new animatefx.animation.ZoomIn(pnObrigado).setSpeed(1.7).play();;
        System.out.println("morro aqui");

    }

    private void voltaObrigado(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0));
        slide.setNode(pnObrigado);
        slide.setToY(608);
        slide.play();
    }
   
    private void preLoadDadosUsuario(){
        Image usuImage = new Image("/images/cartaoPadrao.png", false);
        imgCartao.setFill(new ImagePattern(usuImage));
    }
}
