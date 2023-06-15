package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Comentario;

public class DaoComentario{
    private PreparedStatement declaracao;
    private String command = "";

    public ResultSet findByIdJogo(int idJogo){
        ConnectBd bd = new ConnectBd();

        try{
            command = "SELECT Comentario.idComentario, Usuario.nomeUsuario, Comentario.comentario, Usuario.imgUsuario FROM Comentario, Usuario WHERE Comentario.idJogo = ? AND Usuario.idUsuario = Comentario.idUsuario";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, idJogo);
            ResultSet resultado = declaracao.executeQuery();
            System.out.println("Transacao realizada com sucesso! ACHOU ALGUM COMENTARIO");
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

    public void insertComentario(Comentario coment){
        ConnectBd bd = new ConnectBd();
        try{
            command = "INSERT INTO comentario VALUES (NULL, ?, ?, ?)";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, coment.getIdjogo());
            declaracao.setInt(2, coment.getIdUsuario());
            declaracao.setString(3, coment.getComentario());
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Adicionou com sucesso o comentario ao banco!");
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

    public void deleteComentario(int id){
        ConnectBd bd = new ConnectBd();
        
        try{
            command = "DELETE FROM Comentario WHERE idComentario = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, id);
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

    public void deleteUsingIdUsu(int id){
        ConnectBd bd = new ConnectBd();
        
        try{
            command = "DELETE FROM Comentario WHERE idUsuario = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, id);
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
}

