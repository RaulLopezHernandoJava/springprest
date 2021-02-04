package com.spring.mvc.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.mvc.entidades.Cliente;

// Extiende de repositorio que permite paginacion y ordenacion
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

	List<Cliente> findByApellidos(String apellidos);

	@Query("select c.id from Cliente c where c.nombre = :nombre and c.apellidos = :apellidos")
	Long findIdByNombreApellidos(String nombre, String apellidos);

}
