package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                comentario.setNomeUsuario(resultado.getString("nomeUsuario"));
                comentario.setImgUsuario(resultado.getString("imgUsuario"));
                comentario.setComentario(resultado.getString("comentario"));
                comentariosUsu.add(comentario);
            }
            return comentariosUsu;
        }
        catch(SQLException e){
            System.out.println("Problema com a transacao!");
            return null;
        }
    }
    // private int idComentario;

    // private int idjogo;

    // private int idUsuario;

    // private String nomeUsuario;

    // private String imgUsuario;

    // private String comentario;


    public void addComentario(String tfComentario){
        Comentario comentario = new Comentario();
        DaoComentario dct = new DaoComentario();

        comentario.setIdjogo(Main.idJogoAux);
        comentario.setIdUsuario(Main.idIdent);
        comentario.setComentario(tfComentario);

        dct.insertComentario(comentario);
    }


}
