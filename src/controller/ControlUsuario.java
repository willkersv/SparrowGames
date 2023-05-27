package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import dao.DaoUsuario;
import model.Usuario;

public class ControlUsuario{
    
    public void consultarLogin(Usuario us){
        try {
            DaoUsuario du = new DaoUsuario();
            ResultSet confirma = du.findByEmail(us);
            if(confirma.next()){
                System.out.println("login com sucesso");
            }else{
                System.out.println("LOGIN NEGADO");
            }
        } catch (SQLException e) {
            System.out.println("Problema com a transacao!");     
        }
    }

    public void cadastraUsuario(Usuario us){
        try{
            DaoUsuario du = new DaoUsuario();
            ResultSet confirma = du.findByEmail(us);
            if(confirma.next()){
                System.out.println("usuario ja cadastrado");
            }
            else{
                du.insertUser(us);
                System.out.println("usuario cadastrado");
            }
        }catch(SQLException e){
            System.out.println("deu erro kk");
        }
    }
}
