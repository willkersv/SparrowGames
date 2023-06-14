package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class DaoUsuario {
    
    private PreparedStatement declaracao;
    private String command = "";
    
    public ResultSet confirmaLogin(Usuario us){
        ConnectBd bd = new ConnectBd();
        
        try{
            command = "SELECT * FROM Usuario WHERE emailUsuario = ? AND senhaUsuario = ? ";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1, us.getEmailUsuario());
            declaracao.setString(2, us.getSenhaUsuario());
            ResultSet resultado = declaracao.executeQuery();
            System.out.println("Transacao realizada com sucesso!");
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

    public ResultSet confirmaCodRec(String email, String codRec){
        ConnectBd bd = new ConnectBd();
        
        try{
            command = "SELECT * FROM Usuario WHERE emailUsuario = ? AND codRec = ? ";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1, email);
            declaracao.setString(2, codRec);
            ResultSet resultado = declaracao.executeQuery();
            System.out.println("Transacao realizada com sucesso!");
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

    public ResultSet findById(int idUsuario){
        ConnectBd bd = new ConnectBd();

        try{
            command = "SELECT * FROM Usuario WHERE idUsuario = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, idUsuario);
            ResultSet resultado = declaracao.executeQuery();
            System.out.println("Transacao realizada com sucesso!");
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
            command = "INSERT INTO usuario VALUES (NULL, ?, ?, ?, ?, ?, 0)";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1, us.getImgUsuario());
            declaracao.setString(2, us.getNomeUsuario());
            declaracao.setString(3, us.getEmailUsuario());
            declaracao.setString(4, us.getSenhaUsuario());
            declaracao.setString(5, us.getCodRec());
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
            command = "DELETE cFROM Comentario, Carrinho, Pagamento, Biblioteca, Desejo, Usuario WHERE idUsuario = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1, id);
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Transacao realizada com sucesso!");

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

    public void updateNomeUsuario(String novoNome){
        ConnectBd bd = new ConnectBd();                                                       
                                                                                        
        try{                                                                                 
            command = "UPDATE usuario SET nomeUsuario = ? WHERE emailUsuario = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1, novoNome);
            declaracao.setString(2, controllerFxml.Main.emailIdent);
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Transacao de uptade realizada com suceso!");
        }
        catch(SQLException ex){
            try{
                bd.getConnection().rollback();
                System.out.println("Transacao de update cancelada!");
                System.err.println("Erro na transacao de uptade: " + ex.getMessage());
            }
            catch(SQLException exe){
                System.err.println("Erro ao cancelar transacao: " + exe.getMessage());
            }
            
        }
    }

    public void updateSenhaUsuario(String senha, String codRec){
        ConnectBd bd = new ConnectBd();                                                       
                                                                                        
        try{                                                                                 
            command = "UPDATE usuario SET senhaUsuario = ?, codRec = ? where emailUsuario = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1, senha);
            declaracao.setString(2, codRec);
            declaracao.setString(3, controllerFxml.Main.emailIdent);
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Transacao de uptade realizada com suceso FERINHA, mudou a senha!");
        }
        catch(SQLException ex){
            try{
                bd.getConnection().rollback();
                System.out.println("Transacao de update cancelada!");
                System.err.println("Erro na transacao de uptade: " + ex.getMessage());
            }
            catch(SQLException exe){
                System.err.println("Erro ao cancelar transacao: " + exe.getMessage());
            }
            
        }
    }


}
