


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
            estaciones[estacionRandom].entrar();
            if (estaciones[estacionRandom].bicicletasDisponibles() == 0) {
                estaciones[estacionRandom].salir();
                return; // no hay bicis, el cliente se va ( corregir despues )
            }
            Bicicleta bicicleta = estaciones[estacionRandom].sacarBicicleta();  // aca se el cambia el estado a en uso
            estaciones[estacionRandom].salir();

            
            clientes[clienteRandom].setBicicleta(bici);

            bicicleta.usar(); // esto es para que aumente el uso y cada un tiempo se la manda a mantenimiento
            Thread.sleep(2000); // tiempo de uso (cambiar a num random)

            // seleccionar estacion random para devolver la bici
            int estacionDevolucion = (int)(Math.random()*estaciones.length);

            // aca hay q ver si el cliente espera o se va a otra estacion
            boolean encontroEstacion = false;
            do {
                estaciones[estacionDevolucion].entrar();
                if (!estaciones[estacionDevolucion].tieneEspacio()) {
                    estaciones[estacionDevolucion].salir();
                    estacionDevolucion = (int)(Math.random()*estaciones.length);
                }
                else {
                    encontroEstacion = true;
                }
            } while(!encontroEstacion);
            
            estaciones[estacionDevolucion].devolverBicicleta(bicleta);

            clientes[clienteRandom].setBicicleta(null);

            estaciones[estacionDevolucion].salir();


        }).start();
        
        Thread.sleep(1000);
    }
    
}

