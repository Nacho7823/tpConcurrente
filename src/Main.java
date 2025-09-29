
public static void main(String[] args) {
    
    
    // crear 3 estaciones
    Estacion[] estaciones = new Estacion[3];
    for (int i = 0; i < 3; i++) {
        estaciones[i] = new Estacion(i, 10);
    }

    // crear 6 clientes
    Cliente[] clientes = new Cliente[6];
    for (int i = 0; i < 6; i++) 
        clientes[i] = new Cliente(i);
    
    int numOfBicicletas = 26; // random number < all(estacion).capacidad

    // crear 20 bicicletas y distribuirlas aleatoriamente en las estaciones
    Bicicleta[] bicicletas = new Bicicleta[numOfBicicletas];
    for (int i = 0; i < numOfBicicletas; i++) {
        int estacionIndex = (int) (Math.random() * estaciones.length);
        bicicletas[i] = new Bicicleta(i, estaciones[estacionIndex]);
        estaciones[estacionIndex].addBicicleta(bicicletas[i]);
    }

    // crear camioneta
    Camioneta camioneta = new Camioneta(estaciones);
    camioneta.start();


    // Parallel
    new Thread(() -> {
        while(true) {
            camioneta.revisarEstaciones();
        }
    }).start();

    while(true) {
        // tomamos un cliente random y una estacion random
        int clienteRandom = (int)(Math.random()*clientes.length);
        int estacionRandom = (int)(Math.random()*estaciones.length);
        // hacemos que el cliente pida una bici de la estacion
        new Thread(() -> {
            Bicicleta bici = estaciones[estacionRandom].sacarBicicleta();
            clientes[clienteRandom].setBicicleta(bici);

            Thread.sleep(2000); // tiempo de uso (cambiar a num random)
            bicicleta.usar(); // esto es para que aumente el uso y cada un tiempo se la manda a mantenimiento

            // seleccionar estacion random para devolver la bici
            int estacionDevolucion = (int)(Math.random()*estaciones.length);

            estaciones[estacionDevolucion].devolverBicicleta(bici);
            clientes[clienteRandom].setBicicleta(null);
        });
        
        Thread.sleep(1000);
    }
    
}

