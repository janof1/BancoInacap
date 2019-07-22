package cl.inacap.temubank.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.inacap.temubank.models.Transacciones;

@Repository
public interface TransaccionesRepository extends CrudRepository<Transacciones, Long>{
	
	List<Transacciones> findByCuentaOrigenId(Long id);
	List<Transacciones> findByCuentaDestinoId(Long id);

}
