package tpo1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Obtiene clientes desde un archivo CSV.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class Clientes {
    
    private static final String ARCHIVO_CLIENTES = "src/tpo1/clientes.csv";

    /**
     * Devuelve arreglo de clientes.
     * 
     * @return arreglo de clientes
     */
    public static Cliente[] obtenerClientes() {
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
    
}
