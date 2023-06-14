package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import controllerFxml.Main;
import dao.DaoUsuario;
import model.Usuario;

public class ControlUsuario{
    
    public boolean consultarLogin(String email, String senha){
        try {
            Usuario us = new Usuario();
            us.setEmailUsuario(email);
            us.setSenhaUsuario(senha);
            DaoUsuario du = new DaoUsuario();
            ResultSet resultado = du.confirmaLogin(us);
            if(resultado.next()){
                Main.idIdent = resultado.getInt("idUsuario");
                Main.usuImg = resultado.getString("imgUsuario");
                Main.nomeUsuario = resultado.getString("nomeUsuario");
                Main.emailIdent = resultado.getString("emailUsuario");
                Main.verAdmin = resultado.getBoolean("verAdmin");
                System.out.println("login com sucesso");
                return true;
            }else{
                System.out.println("senha e/ou email errado, fera kk"); 
                return false;  
            }
        } catch (SQLException e) {
            System.out.println("Problema com a transacao!");     
        }
        return false;
    }

    public Usuario findByIdUsu(int idUsu){
        try{
            Usuario us = new Usuario();
            DaoUsuario du = new DaoUsuario();
            ResultSet resultado = du.findById(idUsu);
            if(resultado.next()){
                us.setImgUsuario(resultado.getString("ImgUsuario"));
                us.setNomeUsuario(resultado.getString("nomeUsuario"));
                return us;
            }
            else{
                System.out.println("Usuario não existe!!!!!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Problema com a transacao!"); 
            return null;    
        }
    }

    public String consultaCodRec(String email, String codRec){
        try {
            DaoUsuario du = new DaoUsuario();
            ResultSet resultado = du.confirmaCodRec(email, codRec);
            if(resultado.next()){
                System.out.println("Pesquisa deu certo kkk");
                return resultado.getString("CodRec");
                
            }else{
                System.out.println("N encontrou");
                return "";
                   
            }
        } catch (SQLException e) {
            System.out.println("Problema com a transacao!");     
        }
        return ""; 
    }

    public boolean cadastrarUsuario(Usuario us){
        try{
            DaoUsuario du = new DaoUsuario();
            ResultSet confirma = du.confirmaLogin(us);
            if(confirma.next()){
                System.out.println("usuario ja cadastrado");
                return false;
            }
            else{
                du.insertUser(us);
                System.out.println("usuario cadastrado");
                return true;
            }
        }catch(SQLException e){
            System.out.println("deu erro kk");
            return false;
        }    
    }

    public void alterarNomeUsuario(String novoNome){
        DaoUsuario du = new DaoUsuario();
        du.updateNomeUsuario(novoNome);
        System.out.println("usuario alterado");
    }

    public void alterarSenhaUsuario(String novaSenha, String codRec){
        DaoUsuario du = new DaoUsuario();
        du.updateSenhaUsuario(novaSenha, codRec);
        System.out.println("usuario alterado com sucesso rsrs");
    }

    
    public String codRecuperarSenha(){
        String alphaNum = "ABCDEFGHIJKLMNOIPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        int length = 6; 
        for(int i = 0; i < length; i++){
            int index = random.nextInt(alphaNum.length());
            char randomChar = alphaNum.charAt(index);
            sb.append(randomChar);
        }

        String codRecSenha = sb.toString();
        System.out.println("Codigo de recuperacao de senha: " + codRecSenha);
        return codRecSenha;
    }

    public void attNome(String novoNome){
        DaoUsuario du = new DaoUsuario();
        du.updateNomeUsuario(novoNome);
    }

}
