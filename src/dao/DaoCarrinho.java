package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllerFxml.Main;

public class DaoCarrinho{
    private PreparedStatement declaracao;
    private String command = "";

    public ResultSet findByIdUsu(){
        ConnectBd bd = new ConnectBd();

        try{
            command = "SELECT Jogo.idJogo, Jogo.nomeJogo, Jogo.precoJogo, Jogo.imgJogo FROM jogo, carrinho WHERE Carrinho.idUsuario = ? and Carrinho.idJogo = Jogo.idJogo;";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, Main.idIdent);
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

    public boolean insertJogoCarrinho(int idUsuario, int idJogo){
        ConnectBd bd = new ConnectBd();
        try{
            command = "INSERT INTO Carrinho VALUES (?,?)";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, idUsuario);
            declaracao.setInt(2, idJogo);
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Adicionou com sucesso o produto no carrinho!");
            return true;
        }
        catch(SQLException ex){
            try{
                bd.getConnection().rollback();
                System.out.println("Transacao de adicao cancelada!");
                System.err.println("Erro na transacao na adicao: " + ex.getMessage());
                return false;
            }
            catch(SQLException exe){
                System.err.println("Erro ao cancelar a transacao de adicao: " + exe.getMessage());
                return false;
            }
        }
    }

    public void limparCarrinho(){
        ConnectBd bd = new ConnectBd();                                                     
                                                                                        
        try{                                                                                 
            command = "DELETE * FROM Carrinho WHERE idUsuario = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, Main.idIdent);
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

    public void excluirJogoCarrinho(int idJogo){
        ConnectBd bd = new ConnectBd();

        try{
            command = "DELETE FROM Carrinho WHERE idUsuario = ? AND idJogo = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, Main.idIdent);
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