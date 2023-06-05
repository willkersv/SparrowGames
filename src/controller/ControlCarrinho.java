package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controllerFxml.Main;
import dao.DaoCarrinho;
import dao.DaoJogo;
import dao.DaoKey;
import model.Carrinho;

public class ControlCarrinho {

    DaoCarrinho dcr;
    DaoJogo djg;
    DaoKey dky;

    public void addJogoCarrinho(int idJogo){ //A key ainda n entra na brincadeira, so entra quando for finalizar a compra!!!!!!!
        dcr.insertJogoCarrinho(Main.idIdent, idJogo);
    }

    public void removerJogoCarrinho(int idJogo){
        dcr.excluirJogoCarrinho(Main.idIdent, idJogo);
    }

    public void limparCarrinho(){
        dcr.limparCarrinho(Main.idIdent);
    }
    
    public void finalizarCompra(ArrayList<Carrinho> carrinho) throws SQLException{
        for(Carrinho jogoCarrinho : carrinho){ //FINALIZAR AQUI!!!!!!!!!!!!!!!!!!!!!!!!!!!
            ResultSet resultKey = dky.findByIdJogo(jogoCarrinho.getIdJogo());
            jogoCarrinho.setIdSerial(resultKey.getString("idSerial"));
            dcr.atribuirKeyCarrinho(jogoCarrinho);
        }
    }
}
