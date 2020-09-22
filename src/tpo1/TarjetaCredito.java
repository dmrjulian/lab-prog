package tpo1;

import java.time.LocalDate;

/**
 * Tarjeta de crédito de algún cliente del banco.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class TarjetaCredito {

    /**
     * atributos de instancia
     */
    private long numero;// id
    private LocalDate expira;
    private double saldo;
    private double tope;

    /**
     * métodos gets
     */

    public LocalDate getExpira() {
        return expira;
    }

    public long getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getTope() {
        return tope;
    }

    /**
     * métodos sets
     */

    public void setExpira(LocalDate expira) {
        this.expira = expira;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setTope(double tope) {
        this.tope = tope;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)numero;//preguntar
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TarjetaCredito other = (TarjetaCredito) obj;
        if (this.numero == other.numero)
            return false;
        return true;
    }

}
