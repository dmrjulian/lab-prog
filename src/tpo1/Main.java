package tpo1;

/**
 * Clase principal.
 * 
 * @author {@literal Julian Dominguez <julian.dominguez@est.fi.uncoma.edu.ar>}
 * @author {@literal Gabriela Gili <gabriela.gili@est.fi.uncoma.edu.ar>}
 * @author {@literal Diego P. M. Baltar <diego.baltar@est.fi.uncoma.edu.ar>}
 */
public class Main {

    public static void main(String[] args) {
        Cliente cliente;
        CuentaAhorro cuentaAhorro;
        InfoCliente infoCliente;
        
        // Creare cliente
        cliente = new Cliente();
        cliente.setId(1235);
        cliente.setNombre("Diego Baltar");
        cliente.setDireccion("Italia y Jujuy, Neuquén");
        cliente.setTelefono("+5492995215871");
        
        // Agregar 2 cuentas al cliente
        cuentaAhorro = new CuentaAhorro(Divisa.ARS, "0970590940000000001235");
        cuentaAhorro.depositar(57153.86);
        cliente.agregarCuentaAhorro(cuentaAhorro);
        cuentaAhorro = new CuentaAhorro(Divisa.USD, "0970590950000005872852");
        cuentaAhorro.depositar(500.00);
        cliente.agregarCuentaAhorro(cuentaAhorro);
        
        // Generar info del cliente
        infoCliente = new InfoClienteBase();
        
        System.out.println("Ejemplo sin decorador:");
        System.out.println(infoCliente.getInfo(cliente));
        
        infoCliente = new DecoradorInfoCuentas(infoCliente);
        
        System.out.println("Ejemplo con decorador de cuentas:");
        System.out.println(infoCliente.getInfo(cliente));
    }

}
