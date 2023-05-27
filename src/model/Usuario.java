package model;

public class Usuario {
    
    private int idUsuario;
    
    private String nomeUsuaio;

    private String emailUsuario;

    private String senhaUsuario;

    private Boolean verAdmin;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuaio() {
        return nomeUsuaio;
    }

    public void setNomeUsuaio(String nomeUsuaio) {
        this.nomeUsuaio = nomeUsuaio;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public Boolean getVerAdmin() {
        return verAdmin;
    }

    public void setVerAdmin(Boolean verAdmin) {
        this.verAdmin = verAdmin;
    }
}
