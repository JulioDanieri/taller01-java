package py.edu.ucom.julio.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.ucom.julio.entities.TipoDocumento;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {

}