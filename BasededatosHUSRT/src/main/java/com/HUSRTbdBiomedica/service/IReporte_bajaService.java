package com.HUSRTbdBiomedica.service;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.Reporte_baja;


public interface IReporte_bajaService {

	public List<Reporte_baja>ListReportes();
	public Optional<Reporte_baja>ListReportesbyId(Long id);
	public Reporte_baja findOne(Long id);
	public void save(Reporte_baja reporte_baja);
	public void delete(Long id);
	public List<Reporte_baja>ReportesbyBaja(Long id);
	public int countReportesbyBaja(Long id);
	public Long LastIdReporteBaja();
	
	public int countCorrectivosbaja(Date fecha1, Date fecha2);
	public int countPreventivosbaja(Date fecha1, Date fecha2);
	public int countPredictivosbaja(Date fecha1, Date fecha2);
	public int countOtrosbaja(Date fecha1, Date fecha2);
	
	public int countdesgastecorrectivobaja(Date fecha1,Date fecha2);
	public int countopindebidacorrectivobaja(Date fecha1,Date fecha2);
	public int countcausaexcorrectivobaja(Date fecha1,Date fecha2);
	public int countaccesorioscorrectivobaja(Date fecha1,Date fecha2);
	public int countdesconocidocorrectivobaja(Date fecha1,Date fecha2);
	public int countsinfallascorrectivobaja(Date fecha1,Date fecha2);
	public int countotrocorrectivobaja(Date fecha1,Date fecha2);
	
	public int countdesgastebaja(Date fecha1,Date fecha2);
	public int countopindebidabaja(Date fecha1,Date fecha2);
	public int countcausaexbaja(Date fecha1,Date fecha2);
	public int countaccesoriosbaja(Date fecha1,Date fecha2);
	public int countdesconocidobaja(Date fecha1,Date fecha2);
	public int countsinfallasbaja(Date fecha1,Date fecha2);
	public int countotrobaja(Date fecha1,Date fecha2);
}
