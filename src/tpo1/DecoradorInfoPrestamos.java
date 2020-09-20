package tpo1;

/**
 * Decorador de información de préstamos de un cliente.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class DecoradorInfoPrestamos extends DecoradorBase {

    public DecoradorInfoPrestamos(InfoCliente infoCliente) {
        super(infoCliente);
    }
    
    public String getInfo(Cliente cliente) {
        StringBuilder info = new StringBuilder(super.getInfo(cliente));
        Prestamo[] prestamos = cliente.getPrestamos();
        
        info.append("prestamos :").append("\n");
        
        for (int i = 0; i < prestamos.length; i++) {
            Prestamo prestamo = prestamos[i];
            info.append("- divisa      : ")
                .append(prestamo.getDivisa()).append("\n")
                .append("  total       : ")
                .append(prestamo.getTotal()).append("\n")
                .append("  totalPagado : ")
                .append(prestamo.getPagado()).append("\n")
                .append("  cuotas      : ")
                .append(prestamo.getCuotas()).append("\n")
                .append("  cuotasPagas : ")
                .append(prestamo.getCuotasPagas()).append("\n");
        }
        
        return info.toString();
    }

}
