package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoBiblioteca;
import model.Biblioteca;

public class ControlBiblioteca{

    private ArrayList<Biblioteca> biblioteca = new ArrayList<>();

    DaoBiblioteca db = new DaoBiblioteca();

    public ArrayList<Biblioteca> exibirJogosBiblioteca(){
        try{
            ResultSet resultado = db.findByIdUsu();
            while(resultado.next()){
                Biblioteca jogoBiblioteca = new Biblioteca();
                jogoBiblioteca.setIdJogo(resultado.getInt("idJogo"));
                jogoBiblioteca.setNomeJogo(resultado.getString("nomeJogo"));
                jogoBiblioteca.setImgJogo(resultado.getString("imgJogo"));
                jogoBiblioteca.setIdSerial(resultado.getString("idSerial"));
                biblioteca.add(jogoBiblioteca);
                }
            System.out.println();
            return biblioteca;
        }
        catch(SQLException e){
            System.out.println("Problema com a transacao! de adicao na biblitoteca :( )");
            return null;
        }
    }
}
