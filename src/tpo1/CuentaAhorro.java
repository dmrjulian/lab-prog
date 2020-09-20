package tpo1;

/**
 * Cuenta de ahorro de algún cliente del banco.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class CuentaAhorro {

    private final Divisa divisa;
    private final String cbu;
    private double balance;
    
    public CuentaAhorro(Divisa divisa, String cbu) {
        this.cbu = cbu;
        this.divisa = divisa;
    }
    
    public CuentaAhorro() {
        this(null, null);
    }
    
    public Divisa getDivisa() {
        return divisa;
    }
    
    public String getCbu() {
        return cbu;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public double depositar(double cantidad) throws RuntimeException {
        if (balance >= (Integer.MAX_VALUE - cantidad)) {
            throw new RuntimeException("Máximo de dinero excedido");
        }
        
        balance += cantidad;
        
        return balance;
    }
    
    public double retirar(double cantidad) throws RuntimeException {
        if (cantidad > balance) {
            throw new RuntimeException("Fondos insuficientes");
        }

        balance -= cantidad;
        
        return balance;
    }
    
}
