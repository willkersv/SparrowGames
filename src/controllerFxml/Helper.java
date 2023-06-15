package controllerFxml;

import java.io.IOException;
import java.util.function.UnaryOperator;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Helper{

    private static double x = 0, y = 0;

    private static boolean permite = true;

    public static void limitTextField(TextField textField, int limit) {
        UnaryOperator<Change> textLimitFilter = change -> {
            if (change.isContentChange()) {
                int newLength = change.getControlNewText().length();
                if (newLength > limit) {
                    String trimmedText = change.getControlNewText().substring(0, limit);
                    change.setText(trimmedText);
                    int oldLength = change.getControlText().length();
                    change.setRange(0, oldLength);
                }
            }
            return change;
        };
        textField.setTextFormatter(new TextFormatter(textLimitFilter));
    }

    public static void onlyNumbers(TextField tf){
        tf.textProperty().addListener((observable, oldValue, newValue) ->{
            try{
                if(!newValue.equals("")){
                    Long.parseLong(newValue);
                }       
            
            }catch(Exception e){
                tf.setText(oldValue);
            }
        }
        );
    }

    public static void mascaraCPF(TextField textField){

        textField.setOnKeyTyped((KeyEvent event) -> {
            if("0123456789".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando

                if(textField.getText().length()==4){
                    textField.setText(textField.getText().substring(0,3));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==8){
                    textField.setText(textField.getText().substring(0,7));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==12){
                    textField.setText(textField.getText().substring(0,11));
                    textField.positionCaret(textField.getText().length());
                }

            }else{ //escrevendo

                if(textField.getText().length()==14) event.consume();

                if(textField.getText().length()==3){
                    textField.setText(textField.getText()+".");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==7){
                    textField.setText(textField.getText()+".");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==11){
                    textField.setText(textField.getText()+"-");
                    textField.positionCaret(textField.getText().length());
                }

            }

        });
    
    }

    public static void preLoadComum(Pane barra, ImageView btnVoltar, ImageView btnMinimizar, ImageView btnFechar, Label lbNomeUsuario, Circle circleUsu, ImageView imgLupa, 
                                    ImageView imgCarrinho, Button btnTelaConta, Button btnTelaBiblioteca, Button btnTelaDesejo, Button btnTelaAdmin, TextField tfPesquisa, 
                                    Pane pnModal, Line linha4){
        
        
        barra.setOnMousePressed(mouseEvent ->{
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        barra.setOnMouseDragged(mouseEvent ->{
            SceneController.stage.setX(mouseEvent.getScreenX() - x);
            SceneController.stage.setY(mouseEvent.getScreenY() - y);
        });
        
        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                sc.switchTelaInicial(e);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });

        btnMinimizar.setOnMouseClicked((MouseEvent e)->{
            SceneController.stage.setIconified(true);
        });

        btnFechar.setOnMouseClicked((MouseEvent e)->{
            System.exit(1); 
        });

        Image usuImage = new Image(Main.usuImg, false);
        circleUsu.setFill(new ImagePattern(usuImage));
        lbNomeUsuario.setText(Main.nomeUsuario);
        if(Main.verAdmin == false){
            btnTelaAdmin.setVisible(false);
            linha4.setVisible(false);
            pnModal.setMaxHeight(124);
        }

        circleUsu.setOnMouseClicked((MouseEvent e)->{
            if(permite == true){
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(pnModal);
                slide.setByX(-200);
                slide.play();
                pnModal.toFront();
                new animatefx.animation.ZoomIn(pnModal).setSpeed(1.4).play();;
                permite = false;
            }
            else{
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.5));
                slide.setNode(pnModal);  
                slide.setByX(200);
                slide.play();
                pnModal.toFront();
                new animatefx.animation.ZoomOut(pnModal).setSpeed(1.4).play();
                Helper.permite = true;
            } 
        });
        
        imgLupa.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                Main.nomeJogoAux = tfPesquisa.getText();
                permite = true;
                sc.switchTelaBusca(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        
        imgCarrinho.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                permite = true;
                sc.switchTelaCarrinho(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        btnTelaConta.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                permite = true;
                sc.switchTelaConta(e);
            } 
            catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        
        btnTelaBiblioteca.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                permite = true;
                sc.switchTelaBiblioteca(e);
            } 
            catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        btnTelaDesejo.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                permite = true;
                sc.switchTelaDesejo(e);
            } 
            catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        btnTelaAdmin.setOnMouseClicked((MouseEvent e)->{
            SceneController sc = new SceneController();
            try {
                permite = true;
                sc.switchTelaAdmin(e);
            } 
            catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
}

