package model;

public class Biblioteca {
    
    private int idUsuario;

    private int idJogo;

    private String nomeJogo;

    private String imgJogo;

    private String idSerial;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public String getImgJogo() {
        return imgJogo;
    }

    public void setImgJogo(String imgJogo) {
        this.imgJogo = imgJogo;
    }

    public String getIdSerial() {
        return idSerial;
    }

    public void setIdSerial(String idSerial) {
        this.idSerial = idSerial;
    }

}
