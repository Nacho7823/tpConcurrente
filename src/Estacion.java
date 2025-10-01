import java.util.Queue;

public class Estacion {

    Queue<Bicicleta> bicicletas;
    private int id;
    private int capacidad;

    public Estacion(int id, int capacidad) {
        this.id = id;
        this.capacidad = capacidad;
        bicicletas = new LinkedList<>();
    }


    public void entrar() {
        // mutex down
    }
    public void salir() {
        // mutex up
    }

    public Bicicleta sacarBicicleta() {
        // mutex
        Bicicleta bici = bicicletas.poll();
        // mutex
        return bici;
    }
    public boolean devolverBicicleta() {
        // mutex
        if (bicicletas.size() >= capacidad)
            return false;
        bicicletas.add(bici);
        // mutex
        return true;
    }

    public boolean addBicicleta(Bicicleta bici) {
        if (bicicletas.size() >= capacidad)
            return false;

        bicicletas.add(bici);
        return true;

    }

    public int bicicletasDisponibles() {
        return bicicletas.size();
    }

    public boolean tieneEspacio() {
        return true; //TODO
    }

}