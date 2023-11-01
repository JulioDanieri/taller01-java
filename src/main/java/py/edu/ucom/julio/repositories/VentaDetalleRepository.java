package py.edu.ucom.julio.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.ucom.julio.entities.VentaDetalle;

public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Integer> {
}