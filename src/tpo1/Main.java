package tpo1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

import Utiles.TecladoIn;


/**
 * Clase principal.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class Main {
    
    private static List<Cliente> clientes;

    public static void main(String[] args) {
        clientes = Clientes.obtenerClientes();
        menu();
    }

    public static void menu() {
        boolean sigue = true;
        int eleccion = 99;
        
        while (sigue) {
            System.out.println("1: Mostrar Clientes Cargados");
            System.out.println("2: Patrón Decorator en la Info. del Cliente");
            System.out.println("3: Executor Service con los hilos(Collable) CuentaTask");
            System.out.println("0: Salir ");

            eleccion = TecladoIn.readLineInt();

            switch (eleccion) {
                case 1:
                    mostrarClientes(clientes);
                    break;
                case 2:
                    pruebaPatronDecorador(clientes);
                    break;
                case 3:
                    pruebaExecutorService(clientes);
                    break;
                case 0:
                    sigue = false;
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * Muestra los clientes cargados.
     * 
     * @param clientes los clientes
     */
    private static void mostrarClientes(List<Cliente> clientes) {
        InfoCliente infoCliente = new InfoClienteBase();
        int cantidad = clientes.size();
        
        for (int i = 0; i < cantidad; i++) {
            System.out.println(infoCliente.getInfo(clientes.get(i)));
        }
    }
    
    private static void pruebaPatronDecorador(List<Cliente> clientes) {
        InfoCliente infoCliente = new InfoClienteBase();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Cliente cliente = clientes.get(random.nextInt(0, clientes.size()));
        
        System.out.println("Información básica de cliente (sin decorador): ");
        System.out.println(infoCliente.getInfo(cliente));
        infoCliente = new DecoradorInfoCuentas(infoCliente);
        System.out.println("Información básica y cuentas de cliente (1 decorador): ");
        System.out.println(infoCliente.getInfo(cliente));
        infoCliente = new DecoradorInfoPrestamos(infoCliente);
        System.out.println("Información básica, cuentas y présamos de cliente (2 decoradores): ");
        System.out.println(infoCliente.getInfo(cliente));
    }
    
    private static void pruebaExecutorService(List<Cliente> clientes) {
        List<Future<Double>> resultadosCuentas = new ArrayList<>();
        List<CuentaTask> tareasCuentas = new ArrayList<>();
        PrestamoTask tareaPrestamos = new PrestamoTask(clientes);
        OrdenarClientesTask tareaOrdenar = new OrdenarClientesTask(clientes.toArray(new Cliente[0]));
        Divisa[] divisas = Divisa.values();
        
        // Creamos el pool para los hilos donde van a ir las tareas
        ExecutorService service = Executors.newFixedThreadPool(3);
        Future<Double> resultadoPrestamos = service.submit(tareaPrestamos);
        Future<String> resultadoOrdenar = service.submit(tareaOrdenar);
        
        // Crear tareas
        for (int i = 0; i < divisas.length; i++) {
            CuentaTask tareaCuenta = new CuentaTask(clientes, divisas[i]);
            Future<Double> resultado = service.submit(tareaCuenta);
            tareasCuentas.add(tareaCuenta);
            resultadosCuentas.add(resultado);
        }

        try {
            DecimalFormat formatter = new DecimalFormat("###,###,###.###");
            
            System.out.print("Clientes ordenados: ");
            System.out.println(resultadoOrdenar.get());
            
            for (int i = 0; i < divisas.length; i++) {
                System.out.println(String.format(
                        "Total cuentas de ahorro en %s: %s",
                        tareasCuentas.get(i).getDivisa(),
                        formatter.format(resultadosCuentas.get(i).get())));
            }
            
            System.out.print("Porcentaje de préstamos pagados: ");
            System.out.println(
                    String.format("%%%.2f",
                            resultadoPrestamos.get() * 100));
            
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

        service.shutdown();

    }

}
