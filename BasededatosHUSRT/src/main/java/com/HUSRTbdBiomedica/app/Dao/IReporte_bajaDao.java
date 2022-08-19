package com.HUSRTbdBiomedica.app.Dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Reporte_baja;

@Repository
public interface IReporte_bajaDao extends CrudRepository<Reporte_baja,Long> {
	
	@Query("SELECT COUNT(rb) from Reporte_baja rb")
    public int countAll();
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"INNER JOIN Baja b ON rb.baja.id_Baja=b.id_Baja "+
    		"WHERE b.id_Baja=?1")
    public int countReportesbyBaja(Long id);
    
    @Query("SELECT rb FROM Reporte_baja rb " + 
    		"INNER JOIN Baja b ON rb.baja.id_Baja=b.id_Baja " + 
    		"WHERE b.id_Baja=?1")    
    public List<Reporte_baja> findReportesbyBaja(Long id);
    
    @Query("SELECT MAX(rb.id_Reporte_baja) FROM Reporte_baja rb")
    public Long findLastIdReporte();
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countnumcorrectivosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=3 AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countnumpreventivosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=4 AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countnumpredictivosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=1 AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countnumotrosbaja(Date fecha1, Date fecha2);
    
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=1 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countdesgastecorrectivobaja(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=2 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countopindebidacorrectivobaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=3 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countcausaexcorrectivobaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=4 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countaccesorioscorrectivobaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=5 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countdesconocidocorrectivobaja(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=6 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countsinfallascorrectivobaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=7 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countotroscorrectivobaja(Date fecha1, Date fecha2);
    
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=1 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countdesgastebaja(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=2 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countopindebidabaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=3 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countcausaexbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=4 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countaccesoriosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=5 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countdesconocidobaja(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=6 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countsinfallasbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=7 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countotrosbaja(Date fecha1, Date fecha2);

}
