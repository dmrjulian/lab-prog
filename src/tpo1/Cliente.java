package tpo1;

import java.util.ArrayList;
import java.util.List;

/**
 * Cliente del banco.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class Cliente {

    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private final List<CuentaAhorro> cuentasAhorro;
    private final List<TarjetaCredito> tarjetasCredito;
    private final List<Prestamo> prestamos;
    
    public Cliente(int id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuentasAhorro = new ArrayList<>();
        this.tarjetasCredito = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }
    
    public Cliente() {
        this(-1, null, null, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public CuentaAhorro[] getCuentasAhorro() {
        return cuentasAhorro.toArray(new CuentaAhorro[0]);
    }

    public TarjetaCredito[] getTarjetasCredito() {
        return tarjetasCredito.toArray(new TarjetaCredito[0]);
    }

    public Prestamo[] getPrestamos() {
        return prestamos.toArray(new Prestamo[0]);
    }
    
    public void agregarCuentaAhorro(CuentaAhorro cuentaAhorro) {
        this.cuentasAhorro.add(cuentaAhorro);
    }

    public void agregarPrestamo(Prestamo prestamo) {
        this.prestamos.add(prestamo);
    }
    
}
