package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoKey{
    private PreparedStatement declaracao;
    private String command = "";

    //criar a de verificar disponibilidade

    public ResultSet findByIdJogo(int idJogo){
        ConnectBd bd = new ConnectBd();

        try{
            command = "SELECT * FROM sparrow.key WHERE idJogo = ? AND disponivel = 1 LIMIT 1";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, idJogo);
            ResultSet resultado = declaracao.executeQuery();
            resultado.next();
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

    public void attDisponivel(String idSerial, boolean disponivel){
        ConnectBd bd = new ConnectBd();//                                 MUDAR A DISPONIBILIDADE PARA FALSE APENAS QUANDO O CARA COMPRAR OS JOGOS, APENAS DEPOIS DO PAGAMENTO.

        try{                                                                                 
            command = "UPDATE sparrow.key SET disponivel = ? WHERE idSerial = ?";
            declaracao = bd.getConnection().prepareStatement(command);
            if(disponivel){
                declaracao.setInt(1, 1);
            }else{
                declaracao.setInt(1, 0);
            }
            declaracao.setString(2, idSerial);
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

    public ResultSet verifQtdKeys(int idJogo){
        ConnectBd bd = new ConnectBd();

        try{
            command = "SELECT COUNT(*) AS qtd_keys FROM sparrow.key WHERE idJogo = ? AND disponivel = 1";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, idJogo);
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

    public boolean insertKey(int idJogo, String IdSerial){
        ConnectBd bd = new ConnectBd();

        try{
            command = "INSERT INTO sparrow.key VALUES (?, ?, true)";
            declaracao = bd.getConnection().prepareStatement(command);
            declaracao.setInt(1, idJogo);
            declaracao.setString(2, IdSerial);
            declaracao.execute();
            bd.getConnection().commit();
            System.out.println("Transacao realizada");
            return true;
        }
        catch(SQLException ex){
            try{
                bd.getConnection().rollback();
                System.out.println("Transacao de adicao cancelada!");
                System.err.println("Erro na transacao: " + ex.getMessage());
                return false;
            }
            catch(SQLException exe){
                System.err.println("Erro ao cancelar transacao: " + exe.getMessage());
                return false;
            }
        }
    }


}