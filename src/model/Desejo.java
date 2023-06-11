package model;

public class Desejo {
    
    private int idJogo;

    private int idUsuario;

    private String imgJogo;

    private String nomeJogo;

    private Double precoJogo; 

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getImgJogo() {
        return imgJogo;
    }

    public void setImgJogo(String imgJogo) {
        this.imgJogo = imgJogo;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public Double getPrecoJogo() {
        return precoJogo;
    }

    public void setPrecoJogo(Double precoJogo) {
        this.precoJogo = precoJogo;
    }

}
