package model;


public class Jogo {
    
    private int idJogo;

    private String imgJogo;

    private String nomeJogo;

    private Double precoJogo;

    private String desenvolvedora;

    private String descricao;

    private String genero;

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
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

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
