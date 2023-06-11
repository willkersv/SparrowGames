package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controllerFxml.Main;
import dao.DaoDesejo;
import model.Desejo;

public class ControlDesejo {
    
    private ArrayList<Desejo> desejoUsu = new ArrayList<>();

    DaoDesejo dd = new DaoDesejo();
    
    public ArrayList<Desejo> exibirDesejo(){
        try{
            ResultSet resultado = dd.findByIdUsu();
            while(resultado.next()){
                Desejo desejo = new Desejo();
                desejo.setIdJogo(resultado.getInt("idJogo"));
                desejo.setNomeJogo(resultado.getString("nomeJogo"));
                desejo.setImgJogo(resultado.getString("imgJogo"));
                desejo.setPrecoJogo(resultado.getDouble("precoJogo"));
                desejoUsu.add(desejo);
            }
            return desejoUsu;
        }
        catch(SQLException e){
            System.out.println("Problema com a transacao!");
            return null;
        }
    }

    public boolean consultaDesejo(){

        ResultSet resultado = dd.findByIdJogo();
        try {
            while(resultado.next()){
                
                if(resultado.getInt("idJogo") == Main.idJogoAux){
                    return true;
                }   
                
            }
        } catch (SQLException e) {
           System.out.println("f transacao do coracao");
            e.printStackTrace();
        }
        return false;
    }

    public void addDesejo(){
        DaoDesejo dd = new DaoDesejo();
        dd.insertDesejo();
    }

    public void excluiDesejo(){
        DaoDesejo dd = new DaoDesejo();
        dd.deleteDesejo();
    }
}
