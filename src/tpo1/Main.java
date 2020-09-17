package tpo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;




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

    public static void pruebaExecutorService(){

        //estas listas simulan ser particiones de las BD
        List<Cliente> lista0 = new ArrayList<>();
        List<Cliente> lista1 = new ArrayList<>();
        List<Cliente> lista2 = new ArrayList<>();
        List<Cliente> lista3 = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            Cliente clienteAleatorio = crearClienteRandom();
            lista0.add(clienteAleatorio);
        }
        for (int i = 0; i < 22; i++) {
            Cliente clienteAleatorio = crearClienteRandom();
            lista1.add(clienteAleatorio);
        }
        for (int i = 0; i < 1000; i++) {
            Cliente clienteAleatorio = crearClienteRandom();
            lista2.add(clienteAleatorio);
        }
        for (int i = 0; i < 5; i++) {
            Cliente clienteAleatorio = crearClienteRandom();
            lista3.add(clienteAleatorio);
        }



        //creamos el pool para los hilos donde van a ir las tareas
        ExecutorService service = Executors.newFixedThreadPool(10);
        

        Future<Double> futuroTarea1 = service.submit(new PrestamoTask(lista1));

        try {
            System.out.println(futuroTarea1.get());


        } catch (Exception e) {
            //TODO: handle exception
        }

        service.shutdown();








    }

    public static Cliente crearClienteRandom(){
        Random r = new Random();

        int id = r.nextInt();
        String nombre = "Cliente: " + r.nextInt(99); 
        String direccion = "Direccion: " + r.nextInt(999);   
        String telefono = "Teléfono: "+r.nextInt(999) +"-"+r.nextInt(9999999);

        Cliente salida = new Cliente(id, nombre, direccion, telefono);


        
        int cuotas = r.nextInt();
        double total = r.nextDouble()*100000; 
        double tasa =  r.nextDouble(); 


        Prestamo p = new Prestamo(cuotas, total, tasa);
        
        p.registrarPago(1, 250.12);
        System.out.println(p.getTotal());
        salida.agregarPrestamo(p);;
        
        return salida;
    }

}
