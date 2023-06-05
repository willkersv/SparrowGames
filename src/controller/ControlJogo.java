package controller;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DaoJogo;
import model.Jogo;

public class ControlJogo {
    
    public Jogo consultaJogo(int id){
        try {
            Jogo jg = new Jogo();
            DaoJogo dj = new DaoJogo();
            ResultSet resultado = dj.findById(id);
            if(resultado.next()){
                jg.setImgJogo(resultado.getString("imgJogo"));
                jg.setNomeJogo(resultado.getString("nomeJogo"));
                System.out.println("Jogo encontrado");
                return jg;  
            }else{
                System.out.println("jogo nao encontrado :(");
                return null;
            }
            
        } catch (SQLException e) {
            System.out.println("Problema com a transacao!");     
        }
        return null;
    }
}
