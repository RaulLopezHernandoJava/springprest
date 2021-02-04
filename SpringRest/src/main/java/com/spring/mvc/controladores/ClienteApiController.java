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

import com.spring.mvc.entidades.Cliente;
import com.spring.mvc.repositorios.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteApiController {
	
	@Autowired
	private ClienteRepository clientes;
	
	// Sacamos todos los clientes de la base de datos
	@GetMapping
	public Iterable<Cliente> get() {
		System.out.println(clientes.findAll());
		return clientes.findAll();
		
	}
	
	// Encontrar cliente por id
	
	@GetMapping("{id}")
	public ResponseEntity<Cliente> get(@PathVariable Long id) {
		Optional<Cliente> cliente = clientes.findById(id);
		
		if(cliente.isPresent()) {
			return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
	}
	
	// Insertar cliente
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente post(@RequestBody Cliente cliente) {
		return clientes.save(cliente);
	}
	
	// Actualizar cliente
	@PutMapping("{id}")
	public ResponseEntity<Cliente> put(@PathVariable Long id, @RequestBody Cliente cliente) {
	System.out.println(cliente);
		if(id == cliente.getId()) {
			if(clientes.existsById(id)) {
				return new ResponseEntity<Cliente>(clientes.save(cliente), HttpStatus.OK);	
			} else {
				return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
			}
						
		} else {
			return new ResponseEntity<Cliente>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// Eliminar cliente
	
	@DeleteMapping("{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Long id) {
		if(clientes.existsById(id)) {
			clientes.deleteById(id);
			return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);	
		} else {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
	}
}
