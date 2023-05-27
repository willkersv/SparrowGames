package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.Usuario;

public class DaoUsuario {
    
    
    private PreparedStatement declaracao;
    private String command = "";
    
    public ResultSet findByEmail(Usuario us){
        ConnectBd bd = new ConnectBd();
        
        try{
            command = "SELECT emailUsuario, senhaUsuario FROM Usuario WHERE emailUsuario = ? AND senhaUsuario = ? ";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1, us.getEmailUsuario());
            declaracao.setString(2, us.getSenhaUsuario());
            ResultSet resultado = declaracao.executeQuery();
            System.out.println("Transação realizada com sucesso!");
            return resultado;
            }
        catch(SQLException ex){
            try{
                bd.getConnection().rollback();
                System.out.println("Transacao cancelada!");
                System.err.println("Erro na transacao: " + ex.getMessage());
                return null;
            }
            catch(SQLException exe){
            
                System.err.println("Erro ao cancelar transacao: " + exe.getMessage());
                return null;
            }
        }
       
    }
}
