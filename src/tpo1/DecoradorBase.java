package tpo1;

/**
 * Decorador base (abstracto).
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public abstract class DecoradorBase implements InfoCliente {

    /**
     * Instancia de InfoCliente.
     */
    private final InfoCliente infoCliente;
    
    /**
     * Constructor con instancia de InfoCliente.
     * 
     * @param infoCliente instancia de InfoCliente
     */
    public DecoradorBase(InfoCliente infoCliente) {
        this.infoCliente = infoCliente;
    }
    
    @Override
    public String getInfo(Cliente cliente) {
        return infoCliente.getInfo(cliente);
    }

}
