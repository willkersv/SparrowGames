package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllerFxml.Main;
import model.Key;

public class DaoBiblioteca{
    private PreparedStatement declaracao;
    private String command = "";

    public ResultSet findByIdUsu(){
        ConnectBd bd = new ConnectBd();

        try{
            command = "SELECT Jogo.idJogo, Jogo.nomeJogo, Jogo.imgJogo Biblioteca.idSerial FROM Jogo, Biblioteca WHERE Biblioteca.idUsuario = ? and Biblioteca.idJogo = Jogo.idJogo;";
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
 
    public void insertJogoBiblioteca(Key key){
        ConnectBd bd = new ConnectBd();
        try{
            command = "INSERT INTO Biblioteca VALUES (?,?,?)";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, Main.idIdent);
            declaracao.setInt(2, key.getidJogo());
            declaracao.setString(3, key.getIdSerial());
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


}