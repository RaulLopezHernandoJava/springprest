package com.spring.mvc.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.mvc.entidades.Cliente;

// Extiende de repositorio que permite paginacion y ordenacion
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

	List<Cliente> findByNombre(String nombre);

	//@Query("select c.id from Cliente c where c.nombre = :nombre and c.codigoPostal = :codigo_postal")
	//Long findIdByNombreCodigoPostal(String nombre, int codigoPostal);

}
