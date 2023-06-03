package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ImagemJogo;
import model.Jogo;

public class DaoJogo{
    private PreparedStatement declaracao;
    private String command = "";

    public ResultSet FindByNome(String nomeJogoPesq){
        ConnectBd bd = new ConnectBd();

        try{//                                                         PESQUISA COM QUALQUER COISA ANTES E QUALQUER COISA DEPOIS / Pesquisa mais generica
            command = "SELECT idJogo, precoJogo, desenvolvedora, descricao FROM Jogo WHERE nomeJogo LIKE %?%";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1, nomeJogoPesq);
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

    public ResultSet FindById(int idJogoPesq){
        ConnectBd bd = new ConnectBd();

        try{//                                                         PESQUISA COM ESPECIFICA / Não tenho crtz de onde usar, mas ja ta feita ;)
            command = "SELECT nomeJogo, precoJogo, desenvolvedora, descricao FROM Jogo WHERE idJogo = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, idJogoPesq);
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






    public void insertjogo(Jogo jg, ImagemJogo imgJg){
        ConnectBd bd = new ConnectBd();
        try{
            command = "INSERT INTO Jogo VALUES (NULL, ?, ?, ?, ?, ?)";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1, jg.getNomeJogo());
            declaracao.setDouble(2, jg.getPrecoJogo());
            declaracao.setString(3, jg.getDesenvolvedora());
            declaracao.setString(4, jg.getDescricao());
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Adicionou com sucesso o jogito ao banco!");
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

    public void deleteJogo(int id){
        ConnectBd bd = new ConnectBd();
        
        try{
            command = "DELETE FROM Jogo WHERE idJogo = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, id);
            declaracao.executeBatch();
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

    public void updateJogo(String novoNome, Double novoPrecoJogo, String novoDesenvolvedoraJogo, String novoDescricaoJogo, String novoGeneroJogo, int idJogo){
        ConnectBd bd = new ConnectBd();                                                       
                                                                                        
        try{                                                                                 
            command = "UPDATE jogo SET nomeJogo = '?', precoJogo = '?', desenvolvedora = '?', descricao = '?', genero = '?' WHERE idJogo = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setString(1, novoNome);
            declaracao.setDouble(1, novoPrecoJogo);
            declaracao.setString(1, novoDesenvolvedoraJogo);
            declaracao.setString(1, novoDescricaoJogo);
            declaracao.setString(1, novoGeneroJogo);
            declaracao.setInt(2, idJogo);
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

}