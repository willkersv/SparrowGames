package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pagamento;

public class DaoPagamento{
    private PreparedStatement declaracao;
    private String command = "";

    public ResultSet findByIdPagamento(int idPagamento){
        ConnectBd bd = new ConnectBd();

        try{
            command = "SELECT * FROM Pagamento WHERE idPagamento = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, idPagamento);
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

    public ResultSet findByIdUsuario(int idUsuario){
        ConnectBd bd = new ConnectBd();

        try{
            command = "SELECT * FROM Pagamento WHERE idUsuario = ?";
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

    public void insertDadosPagamento(Pagamento pgt){
        ConnectBd bd = new ConnectBd();
        try{
            command = "INSERT INTO Pagamento VALUES (NULL, ?, ?, ?, ?)";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, pgt.getCpf());
            declaracao.setDouble(2, pgt.getValor());
            declaracao.setString(3, pgt.getNumCartao());
            declaracao.setInt(4, pgt.getCvv());
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Adicionou com sucesso os dados de pagamento ao banco!");
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

    public void insertPagamentoConfirmado(Pagamento pgt){
        ConnectBd bd = new ConnectBd();
        try{
            command = "INSERT INTO PagamentoEfetuado VALUES (?, ?, ?)";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, pgt.getIdPagamento());
            declaracao.setInt(2, pgt.getIdUsuario());
            declaracao.setInt(3, pgt.getIdJogo());
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Adicionou com sucesso o pagamento confirmado ao banco!");
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