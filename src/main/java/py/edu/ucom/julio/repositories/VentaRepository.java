package py.edu.ucom.julio.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.ucom.julio.entities.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

}