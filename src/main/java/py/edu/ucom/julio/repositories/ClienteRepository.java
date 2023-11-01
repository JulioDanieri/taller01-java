package py.edu.ucom.julio.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.ucom.julio.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}