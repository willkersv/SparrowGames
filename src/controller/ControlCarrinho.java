package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoCarrinho;
import model.Carrinho;

public class ControlCarrinho {
    
    private ArrayList<Carrinho> carrinho = new ArrayList<>();

    DaoCarrinho dcr = new DaoCarrinho();

    public ArrayList<Carrinho> exibirJogosCarrinho(){
        try{
            ResultSet resultado = dcr.findByIdUsu();
            while(resultado.next()){
                Carrinho jogoCarrinho = new Carrinho();
                jogoCarrinho.setIdJogo(resultado.getInt("idJogo"));
                jogoCarrinho.setNomeJogo(resultado.getString("nomeJogo"));
                jogoCarrinho.setImgJogo(resultado.getString("imgJogo"));
                jogoCarrinho.setPrecoJogo(Double.parseDouble(resultado.getString("precoJogo")) );
                carrinho.add(jogoCarrinho);
                }
            System.out.println();
            return carrinho;
        }
        catch(SQLException e){
            System.out.println("Problema com a transacao!");
            return null;
        }
    }

    public void addJogoCarrinho(int idJogo){
        dcr.insertJogoCarrinho();
    }

    public void removerJogoCarrinho(int idJogo){
        dcr.excluirJogoCarrinho(idJogo);
    }

    public void limparCarrinho(){
        dcr.limparCarrinho();
    }
    
}
