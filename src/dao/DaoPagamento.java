package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Pagamento;

public class DaoPagamento{
    private PreparedStatement declaracao;
    private Statement declaracao2;
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

    public int ultimoIdInserido(){
        ConnectBd bd = new ConnectBd();

        try{
            command = "SELECT MAX(idPagamento) as idPagamento FROM Pagamento"; //chega a ser engraçado uma porra dessas kkkkkkkkkkkkkkkkkkk
            declaracao2 = bd.getConnection().createStatement();
            ResultSet resultado = declaracao2.executeQuery(command);
            resultado.next();
            System.out.println("Ultimo id retornado com sucesso: " + resultado.getInt("idPagamento"));
            System.out.println("Transacao realizada com sucesso!");
            return resultado.getInt("idPagamento");
        }
        catch(SQLException ex){
            try{
                bd.getConnection().rollback();
                System.out.println("Transacao cancelada!");
                System.err.println("Erro na transacao: " + ex.getMessage());
                return 0;
            }
            catch(SQLException exe){
                System.err.println("Erro ao cancelar transacao: " + exe.getMessage());
                return 0;
            }
        }
    }
    
    
    
    // public ResultSet ultimoIdInserido(){
    //     ConnectBd bd = new ConnectBd();
    //     try{
    //         command = "SELECT MAX(idPagamento) as idPagamento FROM Pagamento";
    //         declaracao = bd.getConnection().prepareStatement(command);
    //         ResultSet resultado = declaracao.executeQuery();
    //         System.out.println("Ultimo id retornado com sucesso: " + resultado.getInt("idPagamento"));
    //         return resultado;
    //     }catch(SQLException ex){
    //         try{
    //             bd.getConnection().rollback();
    //             System.out.println("Transacao de adicao cancelada!");
    //             System.err.println("Erro na transacao da adicao ai mesmo adjihhahdas: " + ex.getMessage());
    //         }
    //         catch(SQLException exe){
    //             System.err.println("Erro ao cancelar a transacao de adicao: " + exe.getMessage());
    //         }
    //         return null;
    //     }
    // }

    public void insertDadosPagamento(Pagamento pgt){
        ConnectBd bd = new ConnectBd();
        try{
            command = "INSERT INTO Pagamento VALUES (NULL, ?, ?, ?, ?, ?, ?)";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, pgt.getIdUsuario());
            declaracao.setInt(2, pgt.getIdJogo());
            declaracao.setString(3, pgt.getCpf());
            declaracao.setDouble(4, pgt.getValor());
            declaracao.setString(5, pgt.getNumCartao());
            declaracao.setInt(6, pgt.getCvv());
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

    public void deleteUsingIdUsu(int id){
        ConnectBd bd = new ConnectBd();
        
        try{
            command = "DELETE FROM Pagamento WHERE idUsuario = ?";
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

    // public void insertPagamentoConfirmado(Pagamento pgt){
    //     ConnectBd bd = new ConnectBd();
    //     try{
    //         command = "INSERT INTO PagamentoEfetuado VALUES (?, ?, ?)";
    //         declaracao = bd.getConnection().prepareStatement(command);
    //         declaracao.setInt(1, pgt.getIdPagamento());
    //         declaracao.setInt(2, pgt.getIdUsuario());
    //         declaracao.setInt(3, pgt.getIdJogo());
    //         declaracao.execute();
    //         bd.getConnection().commit();
    //         System.out.println("Adicionou com sucesso o pagamento confirmado ao banco!");
    //     }
    //     catch(SQLException ex){
    //         try{
    //             bd.getConnection().rollback();
    //             System.out.println("Transacao de adicao cancelada!");
    //             System.err.println("Erro na transacao na adicao: " + ex.getMessage());
    //         }
    //         catch(SQLException exe){
    //             System.err.println("Erro ao cancelar a transacao de adicao: " + exe.getMessage());
    //         }
    //     }
    // }

}