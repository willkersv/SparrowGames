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

    public void insertUser(Usuario us){
        ConnectBd bd = new ConnectBd();
        try{
            command = "INSERT INTO usuario VALUES (NULL, ?, ?, ?, 0)";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1, us.getNomeUsuario());
            declaracao.setString(2, us.getEmailUsuario());
            declaracao.setString(3, us.getSenhaUsuario());
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Adicionou com sucesso o homem ao banco!");
            }
        catch(SQLException ex){
            try{
                bd.getConnection().rollback();
                System.out.println("Transacao de adicao cancelada!");
                System.err.println("Erro na transacao na adicao: " + ex.getMessage());
            }
            catch(SQLException exe){
                System.err.println("Erro ao cancelar a transacao de adicao: " + exe.getMessage());
            }
        }  
    }

    public void deleteUsuario(String id){
        ConnectBd bd = new ConnectBd();
        
        try{
            command = "DELETE FROM Usuario WHERE idUsuario = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1, id);
            declaracao.executeBatch();
            bd.getConnection().commit();
            System.out.println("Transação realizada com sucesso!");

        }
        catch(SQLException ex){
            try{
                bd.getConnection().rollback();
                System.out.println("Transacao cancelada!");
                System.err.println("Erro na transacao: " + ex.getMessage());
            }
            catch(SQLException exe){
            
                System.err.println("Erro ao cancelar transacao: " + exe.getMessage());
            }
            
        }
    }


}
