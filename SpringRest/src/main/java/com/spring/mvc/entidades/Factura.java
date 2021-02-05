package com.spring.mvc.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="facturas")
@Data @AllArgsConstructor @NoArgsConstructor
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String nombre;
	private String descripcion;
	private Double precio;
	
	@ManyToOne
    @JoinColumn(name="id", insertable=false ,updatable=false)
    private Cliente cliente;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy="factura")
	private final Set<FacturaProducto> facturaProductos = new HashSet<FacturaProducto>();

	
	//@DateTimeFormat(iso = ISO.DATE)
	//private LocalDate fechaLLegada;
	//spring.jpa.hibernate.ddl-auto=update
}
