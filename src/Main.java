


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

    // crear bicicletas y distribuirlas aleatoriamente en las estaciones
    Bicicleta[] bicicletas = new Bicicleta[numOfBicicletas];
    for (int i = 0; i < numOfBicicletas; i++) {
        int estacionIndex = (int) (Math.random() * estaciones.length);
        bicicletas[i] = new Bicicleta(i);
        estaciones[estacionIndex].addBicicleta(bicicletas[i]);
    }

    // crear camioneta
    Camioneta camioneta = new Camioneta(estaciones);

    // Parallel
    new Thread(() -> {
        while(true) {
            camioneta.revisarEstaciones();
            // buscar estaciones con mayor y menor cantidad de bicis
            // llevar bicis de la que tiene mas a la que tiene menos
            // buscar bicicletas que necesitan mantenimiento
            // llevarlas a mantenimiento
        }
    }).start();

    while(true) {
        new Thread(() -> {
            // tomamos un cliente random y una estacion random
            int clienteRandom = (int)(Math.random()*clientes.length);
            int estacionRandom = (int)(Math.random()*estaciones.length);

            // hacemos que el cliente pida una bici de la estacion
            Bicicleta bici = estaciones[estacionRandom].sacarBicicleta();
            clientes[clienteRandom].setBicicleta(bici);

            bicicleta.usar(); // esto es para que aumente el uso y cada un tiempo se la manda a mantenimiento
            Thread.sleep(2000); // tiempo de uso (cambiar a num random)

            // seleccionar estacion random para devolver la bici
            int estacionDevolucion = (int)(Math.random()*estaciones.length);

            // aca hay q ver si el cliente espera o se va a otra estacion
            if (Math.random() % 2 == 0) // espera
            {
                // region critica
                // esperar hasta que haya espacio
                while(!estaciones[estacionDevolucion].tieneEspacio()) {
                    Thread.sleep(100); // espera un poco antes de volver a chequear
                }
                // devolver bici
                estaciones[estacionDevolucion].devolverBicicleta(bici);
                clientes[clienteRandom].setBicicleta(null);

                // region critica
            }
            else {  // se va a otra estacion
                {
                    // region critica
                    while(!estaciones[estacionDevolucion].tieneEspacio()) {
                        estacionDevolucion = (int)(Math.random()*estaciones.length);
                    }
                    // guardar
                    estaciones[estacionDevolucion].devolverBicicleta(bici);
                    clientes[clienteRandom].setBicicleta(null);
                    
                    // region critica
                }
            }


        }).start();
        
        Thread.sleep(1000);
    }
    
}

