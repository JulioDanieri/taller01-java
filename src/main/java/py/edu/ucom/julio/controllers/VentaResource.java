package py.edu.ucom.julio.controllers;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import py.edu.ucom.julio.config.Globales;
import py.edu.ucom.julio.entities.Venta;
import py.edu.ucom.julio.entities.VentaDetalle;
import py.edu.ucom.julio.entities.dto.ResumenVentaDTO;
import py.edu.ucom.julio.service.VentaService;

@Path("/Venta")
public class VentaResource {
    @Inject
    public VentaService service;

    @GET
    @Operation(summary = "Listar ventas", description = "Lista todas las ventas")
    public Response listar() {
        List<Venta> lista = this.service.listar();
        if (lista != null) {
            return Response.status(Response.Status.OK).entity(lista).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(Globales.CRUD.LISTADO_ERR).build();
        }
    }

    @DELETE
    @Operation(summary = "Eliminar venta", description = "Elimina la venta seleccionada por su ID")
    @Path("{id}")
    public Response eliminar(Integer id) {
        try {
            this.service.eliminar(id);
            return Response.status(Response.Status.OK)
                    .entity(Globales.CRUD.ELIMINADO_OK)
                    .build();
        } catch (Exception e) {
            // Manejar la excepción de persistencia
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Globales.CRUD.ELIMINADO_ERR)
                    .build();
        }
    }

    @PUT
    @Operation(summary = "Modificar venta", description = "Modifica la venta seleccionada por su ID, si se desea modificar el total o los productos ir al resource de VentaDetalle e ingresar el ID de la venta a modificar")
    @Path("modificar/{ventaId}")
    public Response modificarVenta(@PathParam("ventaId") Integer ventaId,
            @QueryParam("clienteId") Integer clienteId, @QueryParam("metodoPagoId") Integer metodoPagoId) {
        return this.service.modificarVenta(ventaId, clienteId, metodoPagoId);
    }

    @GET
    @Operation(summary = "Obtener venta", description = "Busca la venta seleccionada por su ID")
    @Path("{id}")
    public Response obtener(@PathParam("id") Integer param) {
        Venta venta = this.service.obtener(param);
        if (venta != null) {
            return Response.status(Response.Status.OK).entity(venta).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(Globales.CRUD.RECUPERADO_ERR).build();
        }
    }

    @GET
    @Operation(summary = "Obtener resumen de venta", description = "Muestra un resumen de la venta seleccionada por su ID")
    @Path("resumen/{id}")
    public ResumenVentaDTO obtenerResumen(@PathParam("id") Integer param) {
        return this.service.obtenerResumen(param);
    }

    @GET
    @Operation(summary = "Listar detalles de venta", description = "Lista todos los detalles de venta relacionados a la venta seleccionada por su ID")
    @Path("listaDetalle/{id}")
    public List<VentaDetalle> listarById(Integer id) {
        Venta venta = this.service.obtener(id);
        return venta.getVentaDetalleList();
    }

    @POST
    @Operation(summary = "Crear venta", description = "Se crea una nueva venta en la base de datos")
    @Path("/agregar/venta/{clienteId}/{metodoPagoId}/{productoId}/{cantidad}")
    public Response crearVenta(@PathParam("clienteId") Integer clienteId,
            @PathParam("metodoPagoId") Integer metodoPagoId,
            @PathParam("productoId") Integer productoId, @PathParam("cantidad") Integer cantidad) {
        return this.service.crearVenta(clienteId, metodoPagoId, productoId, cantidad);
    }

}