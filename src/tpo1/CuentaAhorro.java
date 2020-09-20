package tpo1;

/**
 * Cuenta de ahorro de algún cliente del banco.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class CuentaAhorro {

    /**
     * La divisa de la cuenta.
     */
    private final Divisa divisa;
    
    /**
     * La CBU.
     */
    private final String cbu;
    
    /**
     * El balance de la cuenta.
     */
    private double balance;
    
    /**
     * Constructor con divisa y CBU.
     * 
     * @param divisa la divisa
     * @param cbu la cbu
     */
    public CuentaAhorro(Divisa divisa, String cbu) {
        this.cbu = cbu;
        this.divisa = divisa;
    }
    
    /**
     * Devuelve la divisa de la cuenta.
     * 
     * @return la divisa de la cuenta
     */
    public Divisa getDivisa() {
        return divisa;
    }
    
    /**
     * Devuelve la CBU.
     * 
     * @return la cbu de la cuenta
     */
    public String getCbu() {
        return cbu;
    }
    
    /**
     * Devuelve el balance de la cuenta.
     * 
     * @return el balance de la cuenta
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * Deposita un valor en la cuenta aumentando el balance de la cuenta en la
     * cantidad dada.
     * 
     * @param cantidad la cantidad a depositar
     * @return el nuevo balance luego del depósito
     * @throws RuntimeException si no se pudo depositar
     */
    public double depositar(double cantidad) throws RuntimeException {
        if (balance >= (Integer.MAX_VALUE - cantidad)) {
            throw new RuntimeException("Máximo de dinero excedido");
        }
        
        balance += cantidad;
        
        return balance;
    }
    
    /**
     * Retira un valor en la cuenta reduciendo el balance de la cuenta en la
     * cantidad dada.
     * 
     * @param cantidad la cantidad a retirar
     * @return el nuevo balance luego del retiro
     * @throws RuntimeException si no se pudo retirar
     */
    public double retirar(double cantidad) throws RuntimeException {
        if (cantidad > balance) {
            throw new RuntimeException("Fondos insuficientes");
        }

        balance -= cantidad;
        
        return balance;
    }
    
}
