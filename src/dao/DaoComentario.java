package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}

