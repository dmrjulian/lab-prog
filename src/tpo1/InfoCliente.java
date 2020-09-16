package tpo1;

/**
 * Generador de información acerca de un cliente.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public interface InfoCliente {

    /**
     * Devuelve información acerca del cliente dado.
     * 
     * @param cliente el cliente
     * @return la información contenida en una cadena
     */
    public String getInfo(Cliente cliente);
    
}
