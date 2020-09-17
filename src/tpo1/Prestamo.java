package tpo1;

/**
 * Préstamo adquirido en el banco por algún cliente.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class Prestamo {

    private int cuotas;
    private int cuotasPagas;
    private double total;
    private double pagado;
    private double tasa;
    
    public Prestamo(int cuotas, double total, double tasa) {
        this.tasa = tasa;
        this.cuotas = cuotas;
        this.total = total;
        this.pagado = 0;
    }
    
    public void registrarPago(int cantidadCuotas, double monto) {
        cuotasPagas += cantidadCuotas;
        pagado += monto;
    }

    public int getCuotasPagas() {
        return cuotasPagas;
    }

    public void setCuotasPagas(int cuotasPagas) {
        this.cuotasPagas = cuotasPagas;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }
    
}
