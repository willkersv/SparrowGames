package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controllerFxml.Main;
import dao.DaoComentario;
import model.Comentario;

public class ControlComentario {
    
    private ArrayList<Comentario> comentariosUsu = new ArrayList<>();

    DaoComentario dcm = new DaoComentario();

    public ArrayList<Comentario> exibirComentarios(){
        try{
            ResultSet resultado = dcm.findByIdJogo(Main.idJogoAux);
            while(resultado.next()){
                Comentario comentario = new Comentario();
                comentario.setIdComentario(resultado.getInt("idComentario"));
                System.out.println(resultado.getInt("idComentario"));
                comentario.setNomeUsuario(resultado.getString("nomeUsuario"));
                System.out.println(resultado.getString("nomeUsuario"));
                comentario.setImgUsuario(resultado.getString("imgUsuario"));
                System.out.println(resultado.getString("imgUsuario"));
                comentario.setComentario(resultado.getString("comentario"));
                System.out.println(resultado.getString("comentario"));
                comentariosUsu.add(comentario);
            }
            return comentariosUsu;
        }
        catch(SQLException e){
            System.out.println("Problema com a transacao!");
            return null;
        }
    }
}
