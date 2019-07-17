package cl.inacap.temubank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.inacap.temubank.models.Productos;

@Repository
public interface ProductosRepository extends CrudRepository<Productos, Long>{

}
