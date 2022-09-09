package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "calibracion")
public class Calibracion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_calibracion")
	private Long id_Calibracion;
	
	@Column(name = "fecha_recepcion")    
    private Date Fecha_recepcion;
	
	@Column(name = "fecha_calibracion")
	private Date Fecha_calibracion;
	
	@Column(name ="empresa")
	private String Empresa;
	
	@Column(name ="numero_certificado")
	private String Numero_certificado;
	
	@Column(name ="calibrado_por")
	private String Calibrado_por;
	
	@Column(name ="aprobado_por")
	private String Aprobado_por;
	
	@Column(name = "condiciones")
	private String Condiciones;
	
	@JoinColumn(name ="id_equipo_fk",referencedColumnName ="id_Equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Equipo equipo;

	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID() {
    	return serialVersionUID;
    }
	
	public Long getId_Calibracion() {
		return id_Calibracion;
	}

	public void setId_Calibracion(Long id_Calibracion) {
		this.id_Calibracion = id_Calibracion;
	}

	public Date getFecha_recepcion() {
		return Fecha_recepcion;
	}

	public void setFecha_recepcion(Date fecha_recepcion) {
		Fecha_recepcion = fecha_recepcion;
	}

	public Date getFecha_calibracion() {
		return Fecha_calibracion;
	}

	public void setFecha_calibracion(Date fecha_calibracion) {
		Fecha_calibracion = fecha_calibracion;
	}

	public String getEmpresa() {
		return Empresa;
	}

	public void setEmpresa(String empresa) {
		Empresa = empresa;
	}

	public String getNumero_certificado() {
		return Numero_certificado;
	}

	public void setNumero_certificado(String numero_certificado) {
		Numero_certificado = numero_certificado;
	}

	public String getCalibrado_por() {
		return Calibrado_por;
	}

	public void setCalibrado_por(String calibrado_por) {
		Calibrado_por = calibrado_por;
	}

	public String getAprobado_por() {
		return Aprobado_por;
	}

	public void setAprobado_por(String aprobado_por) {
		Aprobado_por = aprobado_por;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	
	
	
}
