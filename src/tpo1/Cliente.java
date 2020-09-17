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

    /**
     * Id del cliente.
     */
    private int id;
    
    /**
     * Nombre completo del cliente.
     */
    private String nombre;
    
    /**
     * Dirección del cliente.
     */
    private String direccion;
    
    /**
     * Teléfono del cliente.
     */
    private String telefono;
    
    /**
     * Cuentas de ahorro del cliente.
     */
    private final List<CuentaAhorro> cuentasAhorro;
    
    /**
     * Tarjetas de credito del cliente.
     */
    private final List<TarjetaCredito> tarjetasCredito;
    
    /**
     * Préstamos del cliente.
     */
    private final List<Prestamo> prestamos;
    
    /**
     * Constructor.
     * 
     * @param id id del cliente
     * @param nombre nombre completo del cliente
     * @param direccion dirección del cliente
     * @param telefono teléfono del cliente
     */
    public Cliente(int id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuentasAhorro = new ArrayList<>();
        this.tarjetasCredito = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }
    
    /**
     * Constructor vacío.
     */
    public Cliente() {
        this(-1, null, null, null);
    }

    /**
     * Devuelve el id del cliente.
     * 
     * @return el id del cliente
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del cliente.
     * 
     * @param id el id del cliente
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del cliente.
     * 
     * @return el nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * 
     * @param nombre el nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la dirección del cliente.
     * 
     * @return la dirección del cliente
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del cliente.
     * 
     * @param direccion la dirección del cliente
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve el teléfono del cliente.
     * 
     * @return el teléfono del cliente
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del cliente.
     * 
     * @param telefono el teléfono del cliente
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Devuelve un arreglo de cuentas de ahorro del cliente.
     * 
     * @return arreglo de cuentas de ahorro
     */
    public CuentaAhorro[] getCuentasAhorro() {
        return cuentasAhorro.toArray(new CuentaAhorro[0]);
    }

    /**
     * Devuelve un arreglo de tarjetas de crédito del cliente.
     * 
     * @return arreglo de tarjetas de crédito
     */
    public TarjetaCredito[] getTarjetasCredito() {
        return tarjetasCredito.toArray(new TarjetaCredito[0]);
    }

    /**
     * Devuelve un arreglo de pŕestamos del cliente.
     * 
     * @return arreglo de préstamos
     */
    public Prestamo[] getPrestamos() {
        return prestamos.toArray(new Prestamo[0]);
    }
    
    /**
     * Agrega una cuenta de ahorro al cliente.
     * 
     * @param cuentaAhorro la cuenta de ahorro
     * @return verdadero si fue
     */
    public boolean agregarCuentaAhorro(CuentaAhorro cuentaAhorro) {
        return this.cuentasAhorro.add(cuentaAhorro);
    }
    
    /**
     * Elimina una cuenta de ahorro del cliente.
     * 
     * @param cuentaAhorro una cuenta de ahorro del cliente
     * @return verdadero si se eliminó la cuenta, falso en caso contrario
     */
    public boolean eliminarCuentaAhorro(CuentaAhorro cuentaAhorro) {
        return this.cuentasAhorro.remove(cuentaAhorro);
    }

    /**
     * Agrega un préstamo al cliente.
     * 
     * @param prestamo el pŕestamo
     * @return verdadero si fue agregada, falso en caso contrario
     */
    public boolean agregarPrestamo(Prestamo prestamo) {
        return this.prestamos.add(prestamo);
    }

    /**
     * Eliminar un préstamo del cliente.
     * 
     * @param prestamo el préstamo
     * @return verdadero si fue eliminada, falso en caso contrario
     */
    public boolean eliminarPrestamo(Prestamo prestamo) {
        return this.prestamos.remove(prestamo);
    }
    
    /**
     * Agrega una tarjeta de crédito al cliente.
     * 
     * @param tarjetaCredito la tarjeta del cliente
     * @return verdadero se fue agregada, falso en caso contrario
     */
    public boolean agregarTarjetaCredito(TarjetaCredito tarjetaCredito) {
        return this.tarjetasCredito.add(tarjetaCredito);
    }
    
    /**
     * Eliminar una tarjeta de crédito del cliente.
     * 
     * @param tarjetaCredito la tarjeta de crédito
     * @return verdadero si fue eliminada, falso en caso contrario
     */
    public boolean eliminarTarjetaCredito(TarjetaCredito tarjetaCredito) {
        return this.tarjetasCredito.remove(tarjetaCredito);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        Cliente other = (Cliente) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
}
