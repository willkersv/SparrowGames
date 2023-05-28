package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import controlFxml.Main;
import dao.DaoUsuario;
import model.Usuario;

public class ControlUsuario{
    
    public void consultarLogin(Usuario us){
        try {
            DaoUsuario du = new DaoUsuario();
            ResultSet confirma = du.findByEmail(us);
            if(confirma.next()){
                Main.emailIdent = us.getEmailUsuario();
                Main.verAdmin = us.getVerAdmin();
                System.out.println("login com sucesso");
            }else{
                System.out.println("LOGIN NEGADO");
            }
        } catch (SQLException e) {
            System.out.println("Problema com a transacao!");     
        }
    }

    public void cadastrarUsuario(Usuario us){
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

    public void alterarUsuario(String novoNome){
        DaoUsuario du = new DaoUsuario();
        du.updateUsuario(novoNome);
        System.out.println("usuario alterado");
    }


}
