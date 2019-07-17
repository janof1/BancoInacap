package cl.inacap.temubank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.inacap.temubank.models.Roles;

@Repository
public interface RolClienteRepository extends CrudRepository<Roles, Long>{

}
