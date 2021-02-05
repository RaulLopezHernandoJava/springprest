package com.spring.mvc.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.mvc.entidades.Categoria;


public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long>{
	@Query("FROM Categoria c JOIN FETCH c.productos WHERE c.id = :id")
	Categoria findByIdWithProducts(Long id);
}
