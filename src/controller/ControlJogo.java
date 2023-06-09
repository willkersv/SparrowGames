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
                System.out.println("Jogo encontrado");
                jg.setImgJogo(resultado.getString("imgJogo"));
                jg.setNomeJogo(resultado.getString("nomeJogo"));
                jg.setPrecoJogo(resultado.getDouble("precoJogo"));
                jg.setDesenvolvedora(resultado.getString("desenvolvedora"));
                jg.setDescricao(resultado.getString("descricao")); 
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

    public boolean insereJogo(Jogo jg){
         try{
            DaoJogo dj = new DaoJogo();
            ResultSet confirma = dj.confirmaJogoExiste(jg.getNomeJogo());
            if(confirma.next()){
                System.out.println("Jogo ja cadastrado");
                return false;
            }
            else{
                dj.insertJogo(jg);
                System.out.println("Jogo cadastrado no sistema");
                return true;
            }
        }catch(SQLException e){
            System.out.println("deu erro kk");
            return false;
        }    
    }
}
