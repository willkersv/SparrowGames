package model;

public class Key {
    private int idJogo;

    private int idSerial;

    private boolean disponivel;

    public int getidJogo() {
        return idJogo;
    }

    public void setidJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public int getIdSerial() {
        return idSerial;
    }

    public void setIdSerial(int idSerial) {
        this.idSerial = idSerial;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    } 
}
