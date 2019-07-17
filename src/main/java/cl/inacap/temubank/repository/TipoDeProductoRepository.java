package cl.inacap.temubank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.inacap.temubank.models.TipoDeProductos;

@Repository
public interface TipoDeProductoRepository extends CrudRepository<TipoDeProductos, Long>{

}
