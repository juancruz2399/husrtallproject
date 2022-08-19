package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Servicio;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;


@Repository
public interface IServicioDao extends CrudRepository<Servicio,Long>{
	
	
	@Query("SELECT COUNT(s) from Servicio s")
    public int countAll();
	
	@Query("SELECT s FROM Servicio s "+
    		"WHERE s.Nombre_servicio = ?1")
    public Servicio findServiciobyname(String name);
	
    
    @Query("SELECT e FROM Equipo e "+
    		"INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "
    		+"WHERE s.id_Servicio=?1")
    public List<Equipo> findEquiposbyServicio(Long id);
    
    
    @Query("SELECT COUNT(e) from Equipo e "+
    		"INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "+
    		"WHERE e.Periodicidad=?1 AND s.id_Servicio=?2")
    public int countEspecificPeriodicidadbyServicio(int period,Long id);
    
    @Query("SELECT s FROM Servicio s "+
    	    "INNER JOIN Equipo e ON e.servicio.id_Servicio=s.id_Servicio "
    		+"WHERE e.Periodicidad=?1 "+
    	    "GROUP BY s.id_Servicio")
    public List<Servicio> listServiciosByPeriodicidad(int id);
    
    
    @Query("SELECT COUNT(e) from Equipo e "+
    		"INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "+
    		"WHERE s.id_Servicio=?1")
    public int countEspecificPbyServicio(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "
    		+ "WHERE e.Periodicidad = 4 AND s.id_Servicio=?1")
    public List<Equipo> findEquiposCuatrimestralservicio(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "
    		+ "WHERE e.Periodicidad = 3 AND s.id_Servicio=?1")
    public List<Equipo> findEquiposTrimestralservicio(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "
    		+ "WHERE e.Periodicidad = 2 AND s.id_Servicio=?1")
    public List<Equipo> findEquiposSemestralservicio(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "
    		+ "WHERE s.id_Servicio=?1 AND e.Periodicidad=1")
    public List<Equipo> findEquiposAnualservicio(Long id);
}
