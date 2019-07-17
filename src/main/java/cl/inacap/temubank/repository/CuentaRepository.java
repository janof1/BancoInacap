package cl.inacap.temubank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.inacap.temubank.models.Cuenta;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long>{


}
