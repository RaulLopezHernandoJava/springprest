package com.spring.mvc;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

import com.spring.mvc.entidades.Categoria;
import com.spring.mvc.entidades.Cliente;
import com.spring.mvc.entidades.Producto;
import com.spring.mvc.repositorios.CategoriaRepository;
import com.spring.mvc.repositorios.ClienteRepository;
import com.spring.mvc.repositorios.ProductoRepository;

import lombok.extern.java.Log;

@Log
@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}
	
	@Bean
	CommandLineRunner prueba(ClienteRepository repo, CategoriaRepository repoCat, ProductoRepository repoProd) {
		return (args) -> {
			
			
			pruebasClientes(repo);

			// Pruebas de Categoria y Producto

		
			// Guardar Productos con su categoria
			//guardarProductosYCategorias(repoCat,repoProd);
			
			// Mostrar todos los productos y categorias
			//mostrarProductosYCategorias(repoCat);
			
		};
	}
	
	
	private void pruebasClientes(ClienteRepository repo) {
		
		// Insertamos algunos clientes
		
		//repo.save(new Cliente(null,"Areitz","45820598K",667891271,48920));
		//repo.save(new Cliente(null,"Ainara","42220598M",662291271,48910));
		//repo.save(new Cliente(null,"Andoni","45900598L",667891271,48008));
		
		// Eliminamos algun cliente . EL 4 por ejemplo
		
		repo.deleteById(4L);
		
		// Mostramos todos los clientes
		
		for (Cliente c : repo.findAll()) {
			log.info(c.toString());
		}

		// Buscamos cliente por id
		Optional<Cliente> cliente = repo.findById(3L);
		
		// Si existe lo mostramos
		cliente.ifPresent(c -> log.info(c.toString()));

		// Si no existe el cliente uno por defecto
		log.info(cliente.orElse(new Cliente(null,"Desconocido","xxxxxxxxx",000000000,11111)).toString());

		
		for (Cliente c : repo.findByNombre("Areitz")) {
			log.info(c.toString());
		}

		//log.info(repo.findIdByNombreCodigoPostal("Areitz", 48920).toString());

		for (Cliente c : repo.findAll(PageRequest.of(1, 2, Sort.by("nombre")))) {
			log.info(c.toString());
		}

		for (Cliente c : repo.findAll(Sort.by("nombre"))) {
			log.info(c.toString());
		}
	}
	
	// MOSTRAR TODOS LOS PRODUCTOS
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	private void mostrarProductosYCategorias(CategoriaRepository repoCat) {
		Categoria categoria = repoCat.findById(1L).get();
				
		log.info(categoria.toString());
		
		for (Producto producto : categoria.getProductos()) {
			log.info(producto.toString());
		}
	}
	
	//Guardar Productos y Categorias

	@Transactional
	private void guardarProductosYCategorias(CategoriaRepository repoCat, ProductoRepository repoProd) {
		Categoria verduras = new Categoria(null, "Verduras", "Las mejores verduras del norte");

		Producto vainas = new Producto(null, "56789190", "Vainas","Las mejores vainas",2.56 ,verduras);
		Producto brocoli = new Producto(null, "56789190", "Brocoli","El mejor brocoli",2.56 ,verduras);

		repoCat.save(verduras);
		
		repoProd.save(vainas);
		repoProd.save(brocoli);
		
		verduras = null;
		vainas = null;
		brocoli = null;
	}
}
