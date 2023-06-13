package model;

public class Key {
    
    private int idJogo;

    private String idSerial;

    private boolean disponivel;

    public int getidJogo() {
        return idJogo;
    }

    public void setidJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public String getIdSerial() {
        return idSerial;
    }

    public void setIdSerial(String idSerial) {
        this.idSerial = idSerial;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    } 
}
