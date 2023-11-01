package py.edu.ucom.julio.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.ucom.julio.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}