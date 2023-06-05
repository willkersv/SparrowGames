package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import model.Key;

public class DaoKey{
    private PreparedStatement declaracao;
    private String command = "";

    //criar a de verificar disponibilidade

    public ResultSet findByIdJogo(int idJogo){
        ConnectBd bd = new ConnectBd();

        try{
            command = "SELECT idSerial FROM Key WHERE idJogo = ? AND disponivel = 1 LIMIT 1";
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

    public void attDisponivel(String idSerial, boolean disponivel){
        ConnectBd bd = new ConnectBd();//                                 MUDAR A DISPONIBILIDADE PARA FALSE APENAS QUANDO O CARA COMPRAR OS JOGOS, APENAS DEPOIS DO PAGAMENTO.

        try{                                                                                 
            command = "UPDATE Key SET disponivel = ? WHERE idSerial = ?";
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
            command = "SELECT COUNT(*) AS qtd_keys FROM Key WHERE idJogo = ? AND disponivel = 1";
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
}