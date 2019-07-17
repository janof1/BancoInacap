package cl.inacap.temubank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.inacap.temubank.models.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	Cliente findByRut(String rut);

}
