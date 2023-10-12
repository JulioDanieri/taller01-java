package py.edu.ucom.julio.servicios;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.julio.entities.Clientes;
import py.edu.ucom.julio.entities.DetalleCompra;
import py.edu.ucom.julio.entities.Empleados;
import py.edu.ucom.julio.entities.Pagos;
import py.edu.ucom.julio.entities.Productos;
import py.edu.ucom.julio.utils.DataSourceJSON;

@ApplicationScoped
public class PagoService {

    @Inject
    private DataSourceJSON ds;

    public Pagos generarPago(String documentoCliente, String docuEmpleado, String codigo, String cantidad) {
        int codProd = Integer.parseInt(codigo);
        int cantidadProd = Integer.parseInt(cantidad);
        Clientes cliente = this.ds.buscarCliente(documentoCliente);
        Empleados empleado = this.ds.buscarEmpleado(docuEmpleado);
        DetalleCompra detalle = new DetalleCompra(cliente);
        Productos producto = this.ds.buscarProducto(codProd);
        detalle.agregarProductos(producto, cantidadProd);
        Pagos pago = new Pagos(cliente, detalle, empleado);

        this.ds.guardarPagos(pago);

        return pago;
    }

}