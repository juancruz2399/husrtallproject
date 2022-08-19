package com.HUSRTbdBiomedica.app.Dao;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ITipo_equipoDao extends CrudRepository<Tipo_equipo, Long> {

    @Query("SELECT COUNT(t) from Tipo_equipo t")
    public int countAll();
    
    @Query("SELECT t FROM Tipo_equipo t "+
    		"WHERE t.nombre_Tipo_equipo = ?1")
    public Tipo_equipo findTipo_equipobyname(String name);
    
    @Query("SELECT e FROM Equipo e "+
    		"INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+"WHERE t.id_Tipo_equipo=?1")
    public List<Equipo> findEquiposbyTipoEquipo(Long id);
    
    
    @Query("SELECT t FROM Tipo_equipo t "+
    	    "INNER JOIN Equipo e ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+"WHERE e.Periodicidad=?1 "+
    	    "GROUP BY t.id_Tipo_equipo")
    public List<Tipo_equipo> listTipo_equiposByPeriodicidad(int id);
    
    @Query("SELECT COUNT(e) from Equipo e "+
    		"INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Periodicidad=?1 AND t.id_Tipo_equipo=?2")
    public int countEspecificPbyTipoEquipo(int period,Long id);

         
}