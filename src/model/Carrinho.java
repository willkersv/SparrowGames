package model;

public class Carrinho {

    private int idUsuario;

    private int  idJogo;

    private String nomeJogo;

    private Double precoJogo;

    private String imgJogo;


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

    public Double getPrecoJogo() {
        return precoJogo;
    }

    public void setPrecoJogo(Double precoJogo) {
        this.precoJogo = precoJogo;
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
}
