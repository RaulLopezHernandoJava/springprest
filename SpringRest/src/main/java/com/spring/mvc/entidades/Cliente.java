package com.spring.mvc.entidades;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="clientes")
@Data @AllArgsConstructor @NoArgsConstructor
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String nombre;
	private String cif;
	private Integer telefono;
	private Integer codigoPostal;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy="cliente")
	private final Set<Factura> facturas = new HashSet<Factura>();
	//@DateTimeFormat(iso = ISO.DATE)
	//private LocalDate fechaNacimiento;
	
	//spring.jpa.hibernate.ddl-auto=update
}

