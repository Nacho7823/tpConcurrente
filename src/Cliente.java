


public class Cliente {

    private int id;

    private Bicicleta bicicleta; // null si no tiene bici

    public Cliente(int id) {
        this.id = id;
        this.bicicleta = null;
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    public void usandoBicicleta() {
        if (bicicleta != null) {
            bicicleta.usar();
        }
    }
}