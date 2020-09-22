package tpo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.stream.events.Comment;

import Utiles.TecladoIn;


/**
 * Clase principal.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class Main {

    public static void main(String[] args) {
        Cliente cliente;
        CuentaAhorro cuentaAhorro;
        InfoCliente infoCliente;

        // Creare cliente
        cliente = new Cliente();
        cliente.setId(1235);
        cliente.setNombre("Diego Baltar");
        cliente.setDireccion("Italia y Jujuy, Neuquén");
        cliente.setTelefono("+5492995215871");

        // Agregar 2 cuentas al cliente
        cuentaAhorro = new CuentaAhorro(Divisa.ARS, "0970590940000000001235");
        cuentaAhorro.depositar(57153.86);
        cliente.agregarCuentaAhorro(cuentaAhorro);
        cuentaAhorro = new CuentaAhorro(Divisa.USD, "0970590950000005872852");
        cuentaAhorro.depositar(500.00);
        cliente.agregarCuentaAhorro(cuentaAhorro);

        // Generar info del cliente
        infoCliente = new InfoClienteBase();

        System.out.println("Ejemplo sin decorador:");
        System.out.println(infoCliente.getInfo(cliente));

        infoCliente = new DecoradorInfoCuentas(infoCliente);

        System.out.println("Ejemplo con decorador de cuentas:");
        System.out.println(infoCliente.getInfo(cliente));



        pruebaExecutorService();

    }

    public static void pruebaExecutorService() {

        // estas listas simulan ser particiones de las BD
        List<Cliente> lista0 = new ArrayList<>();
        List<Cliente> lista1 = new ArrayList<>();
        List<Cliente> lista2 = new ArrayList<>();
        List<Cliente> lista3 = new ArrayList<>();
        // for (int i = 0; i < 16; i++) {
        //     Cliente clienteAleatorio = crearClienteRandom();
        //     lista0.add(clienteAleatorio);
        // }
        // for (int i = 0; i < 22; i++) {
        //     Cliente clienteAleatorio = crearClienteRandom();
        //     lista1.add(clienteAleatorio);
        // }
        // for (int i = 0; i < 1000; i++) {
        //     Cliente clienteAleatorio = crearClienteRandom();
        //     lista2.add(clienteAleatorio);
        // }
        // for (int i = 0; i < 5; i++) {
        //     Cliente clienteAleatorio = crearClienteRandom();
        //     lista3.add(clienteAleatorio);
        // }

        // creamos el pool para los hilos donde van a ir las tareas
        ExecutorService service = Executors.newFixedThreadPool(10);
        

        Future<Double> futuroTarea1 = service.submit(new PrestamoTask(lista1));

        try {
            System.out.println(futuroTarea1.get());


        } catch (Exception e) {
            // TODO: handle exception
        }

        service.shutdown();

    }

    public static void menu() {

        boolean sigue = true;
        int eleccion = 99;
        int cantClientes;
        int cantPresMax;
        int cantPresMin;
        Cliente[] clts =null;
        while (sigue) {
            System.out.println("1: Mostrar Clientes Cargados");
            System.out.println("2: Mostrar Patrón Decorator en la Info. del Cliente");
            System.out.println("3: Mostrar el uso de Executor Service con los hilos(Collable) CuentaTask");
            System.out.println("4: Crear algunos clientes aleatorios y asignarle prestamos aleatorios");
            System.out.println("5: Mostrar clientes aleatorios con su respectivos prestamos");
            System.out.println("0: Salir ");

            eleccion = TecladoIn.readLineInt();

            switch (eleccion) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    System.out.print("\nIngrese la cantidad de clientes: ");
                    cantClientes = TecladoIn.readLineInt();
                    System.out.print("\nIngrese la cantidad mínima de prestamos: ");
                    cantPresMin = TecladoIn.readLineInt();
                    System.out.print("\nIngrese la cantidad máxima de prestamos: ");
                    cantPresMax = TecladoIn.readLineInt();
                    System.out.println("");
                    if (cantClientes > 0 && cantPresMin > 0 && cantPresMin < cantPresMax) {
                        clts = crearClientesRandom(cantClientes);
                        asignarPrestamosRandom(cantPresMin, cantPresMax, clts);
                    } else {
                        System.out.println("Error: valores invalidos");
                    }
                    break;
                case 5:
                    if (clts!=null) {
                        for (int i = 0; i < clts.length; i++) {
                            System.out.println(clts[i].toString());
                        }
                    } else {
                        System.err.println("Error: todavía no se selecciono la opcion 4");
                    }
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
     * Método encargado de crear un arreglo de clientes aleatorio
    */
    public static Cliente[] crearClientesRandom(int cantClientes) {
        Random r = new Random();
        Cliente[] salida = new Cliente[cantClientes];
        int id;
        String nombre;
        String direccion;
        String telefono;
        for (int i = 0; i < cantClientes; i++) {
            id = r.nextInt();
            nombre = "Cliente: " + id;
            direccion = "Direccion: " + r.nextInt(999);
            telefono = "Teléfono: " + r.nextInt(999) + "-" + r.nextInt(9999999);
            salida[i] = new Cliente(id, nombre, direccion, telefono);
        }
        return salida;
    }
    /**
     * Método encargado de asignar prestamos aleatorios al arreglo de clientes pasado por parametro
    */
    public static void asignarPrestamosRandom(int cantPresMin, int cantPresMax, Cliente[] clientes) {
 
        Random r = new Random();
        int cuotas;
        double total;
        int cantPrestPromedio;
        Prestamo p;
        for (int i = 0; i < clientes.length; i++) {
            cantPrestPromedio = r.nextInt(cantPresMax + 1) - r.nextInt(cantPresMin + 1);
            // sumo uno porque el limite no lo toma
            for (int j = 0; j < cantPrestPromedio; j++) {
                cuotas = r.nextInt(12) + 1;// el +1 es xq minimo todo prestamo tiene una cuota sin pagar
                total = r.nextDouble() * 100000;
                p = new Prestamo(divisaRandom(), total, cuotas, 0);
                clientes[i].agregarPrestamo(p);
            }

        }

    }

    /**
     * Método encargado de retornar una divisa aleatoriamente
    */
    public static Divisa divisaRandom() {
        Random r = new Random();
        Divisa salida = null;
        int d = r.nextInt(3);
        switch (d) {
            case 0:
                salida = Divisa.USD;
                break;
            case 1:
                salida = Divisa.ARS;
                break;
            case 2:
                salida = Divisa.EUR;
                break;
            default:
                break;
        }
        return salida;
    }

}
