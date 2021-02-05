package com.spring.mvc.repositorios;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.mvc.entidades.Producto;


public interface ProductoRepository extends PagingAndSortingRepository<Producto, Long> {

	
}
