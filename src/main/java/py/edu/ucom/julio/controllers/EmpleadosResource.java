package py.edu.ucom.julio.controllers;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import py.edu.ucom.julio.entities.Empleados;
import py.edu.ucom.julio.utils.DataSourceJSON;

@Path("/empleados")
public class EmpleadosResource {
    @Inject
    public DataSourceJSON ds;

    @GET
    @Path("{documento}")
    public Empleados obtenerEmpleadoPorDocumento(@PathParam("documento") String param) {
        return this.ds.buscarEmpleado(param);

    }

    @GET
    public List<Empleados> obtenerEmpleados() {
        List<Empleados> lista = this.ds.obtenerEmpleados();
        return lista;
    }

    @POST
    public void guardarEmpleado(Empleados empleado) {
        try {
            this.ds.guardarEmpleados(empleado);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

}