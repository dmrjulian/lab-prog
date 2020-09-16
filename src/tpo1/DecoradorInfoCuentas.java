package tpo1;

/**
 * Decorador de información de cuentas de cliente.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class DecoradorInfoCuentas extends DecoradorBase {

    public DecoradorInfoCuentas(InfoCliente infoCliente) {
        super(infoCliente);
    }
    
    public String getInfo(Cliente cliente) {
        StringBuilder info = new StringBuilder(super.getInfo(cliente));
        CuentaAhorro[] cuentasAhorro = cliente.getCuentasAhorro();
        
        info.append("cuentasAhorro:").append("\n");
        
        for (int i = 0; i < cuentasAhorro.length; i++) {
            CuentaAhorro cta = cuentasAhorro[i];
            info.append("- cbu     : ").append(cta.getCbu()).append("\n");
            info.append("  divisa  : ").append(cta.getDivisa()).append("\n");
            info.append("  balance : ").append(cta.getBalance()).append("\n");
        }
        
        return info.toString();
    }

}
