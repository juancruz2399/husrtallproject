package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;

public interface ITipo_equipoService {
	public List<Tipo_equipo>ListTipo_equipo();
	public Optional<Tipo_equipo>ListTipo_equipobyId(Long id);
	public Tipo_equipo findOne(Long id);
	public void save(Tipo_equipo tipo_equipo);
	public void delete(Long id);
	
	public List<Equipo> findEquiposbyTipoEquipo(Long id);
	public List<Tipo_equipo> findTipo_equipobyPeriodicidad(int id);
	public int countEbyTipoEquipobyP(int perioid, Long id);
	
	public Tipo_equipo findbyName(String name);

}
