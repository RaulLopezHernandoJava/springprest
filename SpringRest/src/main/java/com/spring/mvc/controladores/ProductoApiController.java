package com.spring.mvc.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mvc.entidades.Producto;
import com.spring.mvc.repositorios.ProductoRepository;

	@RestController
	@RequestMapping("/api/productos")
	public class ProductoApiController {
		
		@Autowired
		private ProductoRepository productos;
		
		// Sacamos todos los productos de la base de datos
		@GetMapping
		public Iterable<Producto> get() {
			return productos.findAll();
			
		}
		
		// Encontrar producto por id
		
		@GetMapping("{id}")
		public ResponseEntity<Producto> get(@PathVariable Long id) {
			Optional<Producto> producto = productos.findById(id);
			
			if(producto.isPresent()) {
				return new ResponseEntity<Producto>(producto.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
			}
		}
		
		// Insertar producto
		
		@PostMapping
		@ResponseStatus(code = HttpStatus.CREATED)
		public Producto post(@RequestBody Producto producto) {
			return productos.save(producto);
		}
		
		// Actualizar cliente
		@PutMapping("{id}")
		public ResponseEntity<Producto> put(@PathVariable Long id, @RequestBody Producto producto) {
			if(id == producto.getId()) {
				if(productos.existsById(id)) {
					return new ResponseEntity<Producto>(productos.save(producto), HttpStatus.OK);	
				} else {
					return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
				}
							
			} else {
				return new ResponseEntity<Producto>(HttpStatus.BAD_REQUEST);
			}
		}
		
		// Eliminar cliente
		
		@DeleteMapping("{id}")
		public ResponseEntity<Producto> delete(@PathVariable Long id) {
			if(productos.existsById(id)) {
				productos.deleteById(id);
				return new ResponseEntity<Producto>(HttpStatus.NO_CONTENT);	
			} else {
				return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
			}
		}

}
