package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBd {
    private final String mysqlURL = "jdbc:mysql://localhost:3306/";
    private final String nomeBD = "sparrow";
    private final String usuario = "Dollyssauro";
    private final String senha = "1001001";
    private Connection connection;
    
    public ConnectBd(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(mysqlURL+nomeBD, usuario, senha);
            connection.setAutoCommit(false);
            System.out.println("Conectado com sucesso aaaaaaaa");
        }
        catch(SQLException ex){
            System.err.println("Erro de conexao: " +ex.getMessage());
        }
        catch(Exception ex){
            System.err.println("Erro geral " + ex.getMessage());
        }
    }
    
    public Connection getConnection(){
        return this.connection;
    }

    public void closeBD(){
        try{
            connection.close();
            System.out.println("Banco fechado, adeus!!!");
        }
        catch(Exception ex){
            System.err.println("Erro ao desconectar: " + ex.getMessage());
        }
    }
}
