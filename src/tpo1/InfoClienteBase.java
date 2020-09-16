package tpo1;

/**
 * Información básica de un cliente.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class InfoClienteBase implements InfoCliente {

    @Override
    public String getInfo(Cliente cliente) {
        StringBuilder info = new StringBuilder();
        
        info.append("---").append("\n");
        info.append("id        : ").append(cliente.getId()).append("\n");
        info.append("nombre    : ").append(cliente.getNombre()).append("\n");
        info.append("direccion : ").append(cliente.getDireccion()).append("\n");
        info.append("telefono  : ").append(cliente.getTelefono()).append("\n");
        
        return info.toString();
    }

}
