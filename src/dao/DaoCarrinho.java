package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Carrinho;

public class DaoCarrinho{
    private PreparedStatement declaracao;
    private String command = "";

    public ResultSet consultCarUsu(int idUsuario){
        ConnectBd bd = new ConnectBd();

        try{
            command = "SELECT * FROM Carrinho WHERE idUsuario = ?";
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

    public void insertJogoCarrinho(int idUsuario, int idJogo){
        ConnectBd bd = new ConnectBd();
        try{
            command = "INSERT INTO Carrinho VALUES (?,?,NULL)";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, idUsuario);
            declaracao.setInt(2, idJogo);
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Adicionou com sucesso o produto no carrinho!");
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

    public void atribuirKeyCarrinho(Carrinho car){
        ConnectBd bd = new ConnectBd();
        try{
            command = "UPDATE carrinho SET Key_idSerial = ? WHERE idUsuario = ? AND idJogo = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1,car.getIdSerial());
            declaracao.setInt(2, car.getIdUsuario());
            declaracao.setInt(3, car.getIdJogo());
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Transacao de uptade realizada com suceso!");
        }
        catch(SQLException ex){
            try{
                bd.getConnection().rollback();
                System.out.println("Transacao de UPDATE cancelada!");
                System.err.println("Erro na transacao na UPDATE: " + ex.getMessage());
            }
            catch(SQLException exe){
                System.err.println("Erro ao cancelar a transacao de UPDATE: " + exe.getMessage());
            }
        }  
    }

    public void limparCarrinho(int idUsuario){
        ConnectBd bd = new ConnectBd();                                                     
                                                                                        
        try{                                                                                 
            command = "DELETE * FROM Carrinho WHERE idUsuario = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, idUsuario);
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Transacao de DELETE realizada com suceso!");
        }
        catch(SQLException ex){
            try{
                bd.getConnection().rollback();
                System.out.println("Transacao de DELETE cancelada!");
                System.err.println("Erro na transacao de DELETE: " + ex.getMessage());
            }
            catch(SQLException exe){
                System.err.println("Erro ao cancelar transacao: " + exe.getMessage());
            }
            
        }
    }

    public void excluirJogoCarrinho(int idUsuario, int idJogo){
        ConnectBd bd = new ConnectBd();

        try{
            command = "DELETE * FROM Carrinho WHERE idUsuario = ? AND idJogo = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, idUsuario);
            declaracao.setInt(2, idJogo);
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Transacao de DELETE realizada com suceso!");
        }
        catch(SQLException ex){
            try{
                bd.getConnection().rollback();
                System.out.println("Transacao de DELETE cancelada!");
                System.err.println("Erro na transacao de DELETE: " + ex.getMessage());
            }
            catch(SQLException exe){
                System.err.println("Erro ao cancelar transacao: " + exe.getMessage());
            }
            
        }
    }

}