package tpo1;

/**
 * Préstamo adquirido en el banco por algún cliente.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class Prestamo {

    /**
     * La divisa de la cuenta.
     */
    private final Divisa divisa;

    /**
     * Cantidad de cuotas.
     */
    private int cuotas;

    /**
     * Cantidad de cuotas pagas.
     */
    private int cuotasPagas;

    /**
     * Total a pagar.
     */
    private double total;

    /**
     * Total pagado.
     */
    private double pagado;

    /**
     * Constructor.
     * 
     * @param divisa la divisa
     * @param total  la cantidad total
     * @param cuotas la cantidad de cuotas
     */
    public Prestamo(Divisa divisa, double total, int cuotas, int cuotasPagas) {
        this.divisa = divisa;
        this.total = total;
        this.cuotas = cuotas;
        this.cuotasPagas = cuotasPagas;
        this.pagado = 0;
    }

    /**
     * Registra un pago al pŕestamo.
     * 
     * @param cantidadCuotas la cantidad de cuotas a cubrir
     * @param monto          el monto pagado
     */
    public void registrarPago(int cantidadCuotas, double monto) {
        cuotasPagas += cantidadCuotas;
        pagado += monto;
    }

    /**
     * Devuelve la divisa del préstamo.
     * 
     * @return la divisa del préstamo
     */
    public Divisa getDivisa() {
        return divisa;
    }

    /**
     * Devuelve la cantidad de cuotas pagas del pŕestamo.
     * 
     * @return la cantidad de cuotas pagas del pŕestamo
     */
    public int getCuotasPagas() {
        return cuotasPagas;
    }

    /**
     * Devuelve la cantidad de cuotas del pŕestamo.
     * 
     * @return la cantidad de cuotas del pŕestamo
     */
    public int getCuotas() {
        return cuotas;
    }

    /**
     * Devuelve el total del pŕestamo.
     * 
     * @return el total del pŕestamo
     */
    public double getTotal() {
        return total;
    }

    /**
     * Devuelve el total pagado.
     * 
     * @return el total pagado
     */
    public double getPagado() {
        return pagado;
    }

}
