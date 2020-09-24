package tpo1;

import java.util.List;
import java.util.concurrent.Callable;



/**
 * Tarea para obtener el total de balance de cuentas.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */

public class PrestamoTask implements Callable<Double> {

    /**
     * Los clientes a recorrer.
     */
    private List<Cliente> clientes;

    /**
     * Constructor.
     * 
     * @param clientes los clientes
     */
    public PrestamoTask(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public Double call() throws Exception {
        Double porcentajePagado = Double.valueOf(0);
        int cantClientes = clientes.size();
        int cantPrestamos = 0;
        for (int i = 0; i < cantClientes; i++) {
            Prestamo[] prestamos = clientes.get(i).getPrestamos();
            for (int j = 0; j < prestamos.length; j++) {
                cantPrestamos++;
                porcentajePagado +=  prestamos[j].getPagado() /prestamos[j].getTotal();
            }
            
        }
        return (porcentajePagado/cantPrestamos);
    }


}
