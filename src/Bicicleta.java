


public class Bicicleta{

    private int id;

    public int uso = 0;

    public Bicicleta(int id) {
        this.id = id;
    }


    public void usar(){
        uso += Math.random()*10; // random entre 0 y 10
    }

    public boolean necesitaMantenimiento(){
        return uso >= 100;
    }

    public void reparar() { // esto solo lo hace el de mantenimiento
        uso = 0;
    }



}