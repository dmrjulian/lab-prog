package tpo1;

import java.util.List;
import java.util.concurrent.Callable;

public class CuentaTask implements Callable<Double>{
        
    private Divisa tipoDivisa; // recibe el tipo de moneda
    private List<Cliente> clientes;

    public CuentaTask(List<Cliente> clientes, Divisa tipoDivisa){
        this.clientes = clientes;
        this.tipoDivisa = tipoDivisa;
    }

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
