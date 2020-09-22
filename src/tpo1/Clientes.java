package tpo1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Obtiene clientes desde un archivo CSV.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public final class Clientes {
    
    private static final String ARCHIVO_CLIENTES = "src/tpo1/clientes.csv";
    
    private static final int[] codigosCbu = {
            5, 7, 11, 14, 15, 16, 17, 18, 20, 27, 29, 30, 34, 44, 45, 46, 60,
            65, 72, 83, 86, 93, 94, 97, 143, 147, 150, 165, 191, 198, 247, 254,
            259, 262, 266, 268, 269, 277, 281, 285, 295, 299, 300, 301, 305,
            384, 386, 389, 405, 406, 408, 413, 415, 428, 431, 432, 434, 437,
            438, 440, 441, 442, 443, 444, 445, 446, 448, 992
    };
    
    private static int numeroCuentaCbu = 1;

    /**
     * Devuelve arreglo de clientes.
     * 
     * @return arreglo de clientes
     */
    public static final Cliente[] obtenerClientes() {
        Cliente[] clientes = null;
        
        try {
            FileReader lectorArchivo = new FileReader(ARCHIVO_CLIENTES);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            String linea = null;
            int numLinea = 1;
            ArrayList<Cliente> listaClientes = new ArrayList<>();
            
            // Ignorar primera linea
            bufferLectura.readLine();
            
            // Leer linea por linea el archivo
            while ((linea = bufferLectura.readLine()) != null) {
                String[] cols = linea.split("\t");
                Cliente cliente = new Cliente();
                
                try {
                    cliente.setId(Integer.valueOf(cols[0]));
                    cliente.setNombre(cols[1]);
                    cliente.setDireccion(cols[2]);
                    cliente.setTelefono(cols[3]);
                } catch (NumberFormatException e) {
                    System.out.println(
                            String.format(
                                    "Error de formato de numero en linea %d"
                                            + ", archivo \"%s\"",
                                    numLinea,
                                    ARCHIVO_CLIENTES
                                    ));
                }
                
                generarCuentasAhorro(cliente);
                generarPrestamos(cliente);
                
                listaClientes.add(cliente);
                numLinea++;
            }
            
            // Convertir lista a arreglo
            if (!listaClientes.isEmpty()) {
                clientes = listaClientes.toArray(new Cliente[0]);
            }

            bufferLectura.close();
        } catch (FileNotFoundException e) {
            System.out.println(
                    String.format(
                            "Archivo \"%s\" no encontrado", ARCHIVO_CLIENTES));
        } catch (IOException e) {
            System.out.println("Error de E/S");
        }
        
        return clientes;
    }
    
    private static final void generarCuentasAhorro(Cliente cliente) {
        CuentaAhorro cuentaAhorro;
        ThreadLocalRandom aleatorio = ThreadLocalRandom.current();
        Divisa divisa;
        double dinero = 0.0;
        int maxCuentas = 1;
        
        // El 100% de los clientes trendrán entre 1 y 5 cuentas en ARS
        // El 80% tendrá alguna(s) cuenta(s) extra en ARS
        do {
            dinero = aleatorio.nextDouble(0, 10000000);
            dinero = Math.round(dinero * 100.0) / 100.0;
            cuentaAhorro = new CuentaAhorro(Divisa.ARS, generarCbu());
            cuentaAhorro.depositar(dinero);
            cliente.agregarCuentaAhorro(cuentaAhorro);
            maxCuentas++;
        } while (aleatorio.nextInt(0, 100) >= 80 && maxCuentas <= 5);
        
        maxCuentas = 0;
        
        // El 50% tendrá alguna(s) cuenta(s) en EUR y/o USD
        while (aleatorio.nextInt(0, 100) >= 50 && maxCuentas <= 5) {
            dinero = aleatorio.nextDouble(0, 10000000);
            dinero = Math.round(dinero * 100.0) / 100.0;
            divisa = aleatorio.nextBoolean() ? Divisa.EUR : Divisa.USD;
            cuentaAhorro = new CuentaAhorro(divisa, generarCbu());
            cuentaAhorro.depositar(dinero);
            cliente.agregarCuentaAhorro(cuentaAhorro);
            maxCuentas++;
        }
    }
    
    private static final String generarCbu() {
        StringBuilder cbuBuilder = new StringBuilder();
        ThreadLocalRandom aleatorio = ThreadLocalRandom.current();
        DecimalFormat formatter = new DecimalFormat("000");
        int codigo = codigosCbu[aleatorio.nextInt(0, codigosCbu.length)];
        
        cbuBuilder.append(formatter.format(codigo));
        formatter = new DecimalFormat("00009");
        cbuBuilder.append(formatter.format(aleatorio.nextInt(0, 9999)));
        formatter = new DecimalFormat("00000000000001");
        cbuBuilder.append(formatter.format(numeroCuentaCbu));
        numeroCuentaCbu += aleatorio.nextInt(0, 1000);
        
        return cbuBuilder.toString();
    }
    
    private static final void generarPrestamos(Cliente cliente) {
        Prestamo prestamo;
        ThreadLocalRandom aleatorio = ThreadLocalRandom.current();
        Divisa[] divisas = Divisa.values();
        int divisa;
        double deuda = 0.0;
        int cuotas = 1;
        int maxPrestamos = 1;
        
        // El 50% de los clientes está con préstamos
        // El 20% de los que tienen préstamos estarán terminados
        while (aleatorio.nextInt(0, 100) >= 50 && maxPrestamos <= 3) {
            deuda = aleatorio.nextDouble(0, 10000000);
            deuda = Math.round(deuda * 100.0) / 100.0;
            cuotas = aleatorio.nextInt(0, 84);
            divisa = aleatorio.nextInt(0, divisas.length);
            prestamo = new Prestamo(divisas[divisa], deuda, cuotas);
            
            if (aleatorio.nextInt(0, 100) >= 80) {
                prestamo.registrarPago(
                        prestamo.getCuotas() - prestamo.getCuotasPagas(),
                        prestamo.getTotal() - prestamo.getPagado());
            }
            
            cliente.agregarPrestamo(prestamo);
            maxPrestamos++;
        }
    }
    
}
