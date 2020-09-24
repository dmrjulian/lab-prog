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
public class CuentaTask implements Callable<Double> {

    /**
     * Tipo de divisa.
     */
    private Divisa tipoDivisa;
    
    /**
     * Los clientes a recorrer.
     */
    private List<Cliente> clientes;

    /**
     * Constructor con lista de clientes y tipo de divisa a computar.
     * 
     * @param clientes los clientes
     * @param tipoDivisa tipo de divisa
     */
    public CuentaTask(List<Cliente> clientes, Divisa tipoDivisa) {
        this.clientes = clientes;
        this.tipoDivisa = tipoDivisa;
    }
    
    /**
     * Devuelve el tipo de divisa.
     * 
     * @return el tipo de divisa
     */
    public Divisa getDivisa() {
        return tipoDivisa;
    }

    /**
     * Suma el balance total de cuentas de clientes del tipo de divisa dado.
     */
    @Override
    public Double call() throws Exception {
        Double resultado = Double.valueOf(0);
        int cantClientes = clientes.size();

        for (int i = 0; i < cantClientes; i++) {
            CuentaAhorro[] cuentas = clientes.get(i).getCuentasAhorro();
            for (int j = 0; j < cuentas.length; j++) {
                if (cuentas[j].getDivisa() == tipoDivisa) {
                    resultado += cuentas[j].getBalance();
                }
            }
        }

        return resultado;
    }
}
