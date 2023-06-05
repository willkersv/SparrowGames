package controllerFxml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoCarrinho;
import dao.DaoJogo;
import model.Carrinho;

public class TelaCarrinhoController {

    DaoCarrinho dcr;
    DaoJogo djg;

    private ArrayList<Carrinho> carrinho = new ArrayList<>();

    public void exibirCarrinho(){
        try{
            ResultSet resultado = dcr.consultCarUsu(Main.idIdent);
            while(resultado.next()){
                Carrinho jogoCarrinho = new Carrinho();
                jogoCarrinho.setIdUsuario(resultado.getInt("idUsuario"));
                jogoCarrinho.setIdJogo(resultado.getInt("Key_idJogo"));
                jogoCarrinho.setIdSerial(resultado.getString("Key_idSerial"));

                carrinho.add(jogoCarrinho);
            }
        }
        catch(SQLException e){
            System.out.println("Problema com a transacao!");     
        }
    }
}
