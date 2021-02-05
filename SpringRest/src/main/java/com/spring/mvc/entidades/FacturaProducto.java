package com.spring.mvc.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="facturasproductos")
@Data @AllArgsConstructor @NoArgsConstructor
public class FacturaProducto {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)

		private Long id;
		private Integer cantidad;
		private Integer factura_id;
		private Integer producto_id;
		
		@ManyToOne
	    @JoinColumn(name="factura_id", insertable = false, updatable=false)
	    private Factura factura;
		
		@ManyToOne
	    @JoinColumn(name="producto_id", insertable = false, updatable=false)
	    private Producto producto;
		
		//@DateTimeFormat(iso = ISO.DATE)
		//private LocalDate fechaLLegada;
		//spring.jpa.hibernate.ddl-auto=update
	}
