package com.HUSRTbdBiomedica.app.Dao;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Reporte;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEquipoDao extends CrudRepository<Equipo, Long> {

    @Query("SELECT COUNT(e) from Equipo e")
    public int countAll();
        
    @Query("SELECT COUNT(e) from Equipo e WHERE e.Periodicidad=4")
    public int countCuatrimestral();
    
    @Query("SELECT COUNT(e) from Equipo e WHERE e.Periodicidad=3")
    public int countTrimestral();
    
    @Query("SELECT COUNT(e) from Equipo e WHERE e.Periodicidad=2")
    public int countSemestral();
    
    @Query("SELECT COUNT(e) from Equipo e WHERE e.Periodicidad=1")
    public int countAnual();
    

       
    @Query("SELECT e from Equipo e WHERE e.nombre_Equipo LIKE %?1%")
    public List<Equipo> findByNombre(String term);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "WHERE e.Periodicidad = 4 AND t.id_Tipo_equipo=?1")
    public List<Equipo> findEquiposCuatrimestral(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "WHERE e.Periodicidad = 3 AND t.id_Tipo_equipo=?1")
    public List<Equipo> findEquiposTrimestral(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "WHERE e.Periodicidad = 2 AND t.id_Tipo_equipo=?1")
    public List<Equipo> findEquiposSemestral(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "WHERE t.id_Tipo_equipo=?1 AND e.Periodicidad=1")
    public List<Equipo> findEquiposAnual(Long id);
    

    
    @Query("SELECT e FROM Equipo e "+
    		"INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+"WHERE t.id_Tipo_equipo=?1")
    public List<Equipo> findEquiposbyTipoEquipo(Long id);
    
    //para criterios tecnicos
    
    @Query("SELECT count(r) FROM Reporte r "+
    		"INNER JOIN Equipo e ON r.equipo.id_Equipo=e.id_Equipo "
    		+"WHERE r.Tipo_mantenimiento=2 AND e.id_Equipo =?1 AND r.Fecha>=?2 AND r.Fecha<=?3")
    public int numerocorrectivosano(Long id, Date fechainicialano,Date fechaactual);
    
    
    
    //numero mantenimientos preventivos programados
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Enero_mantenimiento='m' OR e.Enero_mantenimiento='M'")
    public int numprevenero();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Febrero_mantenimiento='m' OR e.Febrero_mantenimiento='M'")
    public int numprefebrero();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Marzo_mantenimiento='m' OR e.Marzo_mantenimiento='M'")
    public int numpremarzo();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Abril_mantenimiento='m' OR e.Abril_mantenimiento='M'")
    public int numpreabril();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Mayo_mantenimiento='m' OR e.Mayo_mantenimiento='M'")
    public int numpremayo();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Junio_mantenimiento='m' OR e.Junio_mantenimiento='M'")
    public int numprejunio();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Julio_mantenimiento='m' OR e.Julio_mantenimiento='M'")
    public int numprejulio();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Agosto_mantenimiento='m' OR e.Agosto_mantenimiento='M'")
    public int numpreagosto();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Septiembre_mantenimiento='m' OR e.Septiembre_mantenimiento='M'")
    public int numpreseptiembre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Octubre_mantenimiento='m' OR e.Octubre_mantenimiento='M'")
    public int numpreoctubre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Noviembre_mantenimiento='m' OR e.Noviembre_mantenimiento='M'")
    public int numprenoviembre();
 
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Diciembre_mantenimiento='m' OR e.Diciembre_mantenimiento='M'")
    public int numprediciembre();
    
    //numcontratoagendados
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Enero_mantenimiento='c' OR e.Enero_mantenimiento='C'")
    public int numcontratovenero();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Febrero_mantenimiento='c' OR e.Febrero_mantenimiento='C'")
    public int numcontratofebrero();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Marzo_mantenimiento='c' OR e.Marzo_mantenimiento='C'")
    public int numcontratomarzo();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Abril_mantenimiento='c' OR e.Abril_mantenimiento='C'")
    public int numcontratoabril();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Mayo_mantenimiento='c' OR e.Mayo_mantenimiento='C'")
    public int numcontratomayo();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Junio_mantenimiento='c' OR e.Junio_mantenimiento='C'")
    public int numcontratojunio();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Julio_mantenimiento='c' OR e.Julio_mantenimiento='C'")
    public int numcontratojulio();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Agosto_mantenimiento='c' OR e.Agosto_mantenimiento='C'")
    public int numcontratoagosto();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Septiembre_mantenimiento='c' OR e.Septiembre_mantenimiento='C'")
    public int numcontratoseptiembre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Octubre_mantenimiento='c' OR e.Octubre_mantenimiento='C'")
    public int numcontratooctubre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Noviembre_mantenimiento='c' OR e.Noviembre_mantenimiento='C'")
    public int numcontratonoviembre();
 
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Diciembre_mantenimiento='c' OR e.Diciembre_mantenimiento='C'")
    public int numcontratodiciembre();
    
    
    //numgarantiaagendados
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Enero_mantenimiento='g' OR e.Enero_mantenimiento='G'")
    public int numgarantiavenero();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Febrero_mantenimiento='g' OR e.Febrero_mantenimiento='G'")
    public int numgarantiafebrero();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Marzo_mantenimiento='g' OR e.Marzo_mantenimiento='G'")
    public int numgarantiamarzo();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Abril_mantenimiento='g' OR e.Abril_mantenimiento='G'")
    public int numgarantiaabril();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Mayo_mantenimiento='g' OR e.Mayo_mantenimiento='G'")
    public int numgarantiamayo();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Junio_mantenimiento='g' OR e.Junio_mantenimiento='G'")
    public int numgarantiajunio();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Julio_mantenimiento='g' OR e.Julio_mantenimiento='G'")
    public int numgarantiajulio();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Agosto_mantenimiento='g' OR e.Agosto_mantenimiento='G'")
    public int numgarantiaagosto();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Septiembre_mantenimiento='g' OR e.Septiembre_mantenimiento='G'")
    public int numgarantiaseptiembre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Octubre_mantenimiento='g' OR e.Octubre_mantenimiento='G'")
    public int numgarantiaoctubre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Noviembre_mantenimiento='g' OR e.Noviembre_mantenimiento='G'")
    public int numgarantianoviembre();
 
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Diciembre_mantenimiento='g' OR e.Diciembre_mantenimiento='G'")
    public int numgarantiadiciembre();
    
    
    //tojavascript
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Enero_mantenimiento='m' OR e.Enero_mantenimiento='M'")
    public List<String>prenameenero();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Febrero_mantenimiento='m' OR e.Febrero_mantenimiento='M'")
    public List<String>prenamefebrero();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Marzo_mantenimiento='m' OR e.Marzo_mantenimiento='M'")
    public List<String>prenamemarzo();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Abril_mantenimiento='m' OR e.Abril_mantenimiento='M'")
    public List<String>prenameabril();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Mayo_mantenimiento='m' OR e.Mayo_mantenimiento='M'")
    public List<String>prenamemayo();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Junio_mantenimiento='m' OR e.Junio_mantenimiento='M'")
    public List<String>prenamejunio();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Julio_mantenimiento='m' OR e.Julio_mantenimiento='M'")
    public List<String>prenamejulio();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Agosto_mantenimiento='m' OR e.Agosto_mantenimiento='M'")
    public List<String>prenameagosto();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Septiembre_mantenimiento='m' OR e.Septiembre_mantenimiento='M'")
    public List<String>prenameseptiembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Octubre_mantenimiento='m' OR e.Octubre_mantenimiento='M'")
    public List<String>prenameoctubre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Noviembre_mantenimiento='m' OR e.Noviembre_mantenimiento='M'")
    public List<String>prenamenoviembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Diciembre_mantenimiento='m' OR e.Diciembre_mantenimiento='M'")
    public List<String>prenamediciembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Enero_mantenimiento='c' OR e.Enero_mantenimiento='C'")
    public List<String> contratomeenero();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Febrero_mantenimiento='c' OR e.Febrero_mantenimiento='C'")
    public List<String> contratomefebrero();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Marzo_mantenimiento='c' OR e.Marzo_mantenimiento='C'")
    public List<String> contratomemarzo();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Abril_mantenimiento='c' OR e.Abril_mantenimiento='C'")
    public List<String> contratomeabril();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Mayo_mantenimiento='c' OR e.Mayo_mantenimiento='C'")
    public List<String> contratomemayo();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Junio_mantenimiento='c' OR e.Junio_mantenimiento='C'")
    public List<String> contratomejunio();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Julio_mantenimiento='c' OR e.Julio_mantenimiento='C'")
    public List<String> contratomejulio();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Agosto_mantenimiento='c' OR e.Agosto_mantenimiento='C'")
    public List<String> contratomeagosto();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Septiembre_mantenimiento='c' OR e.Septiembre_mantenimiento='C'")
    public List<String> contratomeseptiembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Octubre_mantenimiento='c' OR e.Octubre_mantenimiento='C'")
    public List<String> contratomeoctubre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Noviembre_mantenimiento='c' OR e.Noviembre_mantenimiento='C'")
    public List<String> contratomenoviembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Diciembre_mantenimiento='c' OR e.Diciembre_mantenimiento='C'")
    public List<String> contratomediciembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Enero_mantenimiento='g' OR e.Enero_mantenimiento='G'")
    public List<String> garantiameenero();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Febrero_mantenimiento='g' OR e.Febrero_mantenimiento='G'")
    public List<String> garantiamefebrero();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Marzo_mantenimiento='g' OR e.Marzo_mantenimiento='G'")
    public List<String> garantiamemarzo();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Abril_mantenimiento='g' OR e.Abril_mantenimiento='G'")
    public List<String> garantiameabril();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Mayo_mantenimiento='g' OR e.Mayo_mantenimiento='G'")
    public List<String> garantiamemayo();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Junio_mantenimiento='g' OR e.Junio_mantenimiento='G'")
    public List<String> garantiamejunio();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Julio_mantenimiento='g' OR e.Julio_mantenimiento='G'")
    public List<String> garantiamejulio();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Agosto_mantenimiento='g' OR e.Agosto_mantenimiento='G'")
    public List<String> garantiameagosto();

    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Septiembre_mantenimiento='g' OR e.Septiembre_mantenimiento='G'")
    public List<String> garantiameseptiembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Octubre_mantenimiento='g' OR e.Octubre_mantenimiento='G'")
    public List<String> garantiameoctubre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Noviembre_mantenimiento='g' OR e.Noviembre_mantenimiento='G'")
    public List<String> garantiamenoviembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.Diciembre_mantenimiento='g' OR e.Diciembre_mantenimiento='G'")
    public List<String> garantiamediciembre();
    //preventivosagendados
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Enero_mantenimiento='m' OR e.Enero_mantenimiento='M' ORDER BY t.Tiempo_minutos DESC")
    public List<Equipo> prevenero();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Febrero_mantenimiento='m' OR e.Febrero_mantenimiento='M' ORDER BY t.Tiempo_minutos DESC")
    public List<Equipo> prefebrero();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Marzo_mantenimiento='m' OR e.Marzo_mantenimiento='M' ORDER BY t.Tiempo_minutos DESC")
    public List<Equipo> premarzo();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Abril_mantenimiento='m' OR e.Abril_mantenimiento='M' ORDER BY t.Tiempo_minutos DESC")
    public List<Equipo> preabril();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Mayo_mantenimiento='m' OR e.Mayo_mantenimiento='M' ORDER BY t.Tiempo_minutos DESC")
    public List<Equipo> premayo();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Junio_mantenimiento='m' OR e.Junio_mantenimiento='M' ORDER BY t.Tiempo_minutos DESC")
    public List<Equipo> prejunio();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Julio_mantenimiento='m' OR e.Julio_mantenimiento='M' ORDER BY t.Tiempo_minutos DESC")
    public List<Equipo> prejulio();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Agosto_mantenimiento='m' OR e.Agosto_mantenimiento='M' ORDER BY t.Tiempo_minutos DESC")
    public List<Equipo> preagosto();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Septiembre_mantenimiento='m' OR e.Septiembre_mantenimiento='M' ORDER BY t.Tiempo_minutos DESC")
    public List<Equipo> preseptiembre();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Octubre_mantenimiento='m' OR e.Octubre_mantenimiento='M' ORDER BY t.Tiempo_minutos DESC")
    public List<Equipo> preoctubre();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Noviembre_mantenimiento='m' OR e.Noviembre_mantenimiento='M' ORDER BY t.Tiempo_minutos DESC")
    public List<Equipo> prenoviembre();
 
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE e.Diciembre_mantenimiento='m' OR e.Diciembre_mantenimiento='M' ORDER BY t.Tiempo_minutos DESC")
    public List<Equipo> prediciembre();
    
//numcontratoagendados
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Enero_mantenimiento='c' OR e.Enero_mantenimiento='C'")
    public List<Equipo> contratovenero();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Febrero_mantenimiento='c' OR e.Febrero_mantenimiento='C'")
    public List<Equipo> contratofebrero();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Marzo_mantenimiento='c' OR e.Marzo_mantenimiento='C'")
    public List<Equipo> contratomarzo();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Abril_mantenimiento='c' OR e.Abril_mantenimiento='C'")
    public List<Equipo> contratoabril();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Mayo_mantenimiento='c' OR e.Mayo_mantenimiento='C'")
    public List<Equipo> contratomayo();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Junio_mantenimiento='c' OR e.Junio_mantenimiento='C'")
    public List<Equipo> contratojunio();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Julio_mantenimiento='c' OR e.Julio_mantenimiento='C'")
    public List<Equipo> contratojulio();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Agosto_mantenimiento='c' OR e.Agosto_mantenimiento='C'")
    public List<Equipo> contratoagosto();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Septiembre_mantenimiento='c' OR e.Septiembre_mantenimiento='C'")
    public List<Equipo> contratoseptiembre();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Octubre_mantenimiento='c' OR e.Octubre_mantenimiento='C'")
    public List<Equipo> contratooctubre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.Noviembre_mantenimiento='c' OR e.Noviembre_mantenimiento='C'")
    public List<Equipo> contratonoviembre();
 
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Diciembre_mantenimiento='c' OR e.Diciembre_mantenimiento='C'")
    public List<Equipo> contratodiciembre();
    
    
    //garantiaagendados
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Enero_mantenimiento='g' OR e.Enero_mantenimiento='G'")
    public List<Equipo> garantiavenero();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Febrero_mantenimiento='g' OR e.Febrero_mantenimiento='G'")
    public List<Equipo> garantiafebrero();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Marzo_mantenimiento='g' OR e.Marzo_mantenimiento='G'")
    public List<Equipo> garantiamarzo();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Abril_mantenimiento='g' OR e.Abril_mantenimiento='G'")
    public List<Equipo> garantiaabril();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Mayo_mantenimiento='g' OR e.Mayo_mantenimiento='G'")
    public List<Equipo> garantiamayo();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Junio_mantenimiento='g' OR e.Junio_mantenimiento='G'")
    public List<Equipo> garantiajunio();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Julio_mantenimiento='g' OR e.Julio_mantenimiento='G'")
    public List<Equipo> garantiajulio();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Agosto_mantenimiento='g' OR e.Agosto_mantenimiento='G'")
    public List<Equipo> garantiaagosto();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Septiembre_mantenimiento='g' OR e.Septiembre_mantenimiento='G'")
    public List<Equipo> garantiaseptiembre();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Octubre_mantenimiento='g' OR e.Octubre_mantenimiento='G'")
    public List<Equipo> garantiaoctubre();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Noviembre_mantenimiento='g' OR e.Noviembre_mantenimiento='G'")
    public List<Equipo> garantianoviembre();
 
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.Diciembre_mantenimiento='g' OR e.Diciembre_mantenimiento='G'")
    public List<Equipo> garantiadiciembre();
    
    //byserie
    @Query("SELECT e.Serie FROM Equipo e")
    public List<String> listaseries();
    
    @Query("SELECT e FROM Equipo e WHERE e.Serie=?1")
    public Equipo findEquipobySerie(String serie);
    
    //equipos inactivos
    @Query("SELECT e FROM Equipo e WHERE e.Activo=0")
    public List<Equipo> findEquiposinactivos();
   
}
