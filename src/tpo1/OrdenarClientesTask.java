package tpo1;

import java.util.concurrent.Callable;

public class OrdenarClientesTask implements Callable<String> {
    // hilo encargado de ordenar particiones de clientes
    private Cliente[] clientes;

    public OrdenarClientesTask(Cliente[] clientes) {
        this.clientes = clientes;
    }

    @Override
    public String call() throws Exception {
        String salida = "";
        quicksort(clientes, 0, clientes.length - 1);

        for (int i = 0; i < clientes.length; i++) {
            salida += "\nCliente: " + clientes[i].getId();
        }

        return salida;
    }

    public static void quicksort(Cliente[] array, int inicio, int fin) {
        int pivote;
        if (inicio < fin) {
            pivote = dividir(array, inicio, fin);
            quicksort(array, inicio, pivote - 1);// ordeno la lista de los menores
            quicksort(array, pivote + 1, fin);// ordeno la lista de los mayores
        }
    }

    private static int dividir(Cliente[] array, int inicio, int fin) {
        int izq;
        int der;
        Cliente pivote;
        Cliente temp;

        pivote = array[inicio];
        izq = inicio;
        der = fin;
        // Mientras no se crucen los indices
        while (izq < der) {
            while (array[der].getId() < pivote.getId()) {
                der--;
            }
            while ((izq < der) && (array[izq].getId() >= pivote.getId())) {
                izq++;
            }
            // Si todavia no se cruzan los indices seguimos intercambiando
            if (izq < der) {
                temp = array[izq];
                array[izq] = array[der];
                array[der] = temp;
            }
        }
        // Los indices ya se han cruzado, ponemos el pivote en el lugar que le
        // corresponde
        temp = array[der];
        array[der] = array[inicio];
        array[inicio] = temp;
        // Retorna la nueva posicion del pivote
        return der;
    }

    public static void burbujaMejorado(Cliente[] arre) {
        boolean bandera = true;
        Cliente aux;
        int i = 0;
        int tam = arre.length;

        while (i < tam - 1 && bandera) {
            bandera = false;// en el caso que no se realicen intercambios, corta

            for (int j = 0; j < tam - 1 - i; j++) {
                if (arre[j].getId() < arre[j + 1].getId()) {
                    bandera = true;// se realizo un intercambios
                    aux = (arre[j]);
                    arre[j] = arre[j + 1];
                    arre[j + 1] = aux;
                }
            }
            i++;
        }
    }

}
