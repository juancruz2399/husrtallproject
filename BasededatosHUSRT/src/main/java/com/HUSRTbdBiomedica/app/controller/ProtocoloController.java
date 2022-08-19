package com.HUSRTbdBiomedica.app.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;
import com.HUSRTbdBiomedica.app.entity.Protocolo_preventivo;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IMantenimiento_preventivoService;
import com.HUSRTbdBiomedica.service.IProtocolo_preventivoService;
import com.HUSRTbdBiomedica.service.IReporteService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;


@Controller
@RequestMapping
public class ProtocoloController {
	
	@Autowired
	private IProtocolo_preventivoService Protocolo_preventivoService;
	
	@Autowired
	private IEquipoService EquipoService;
	
	@Autowired
	private IMantenimiento_preventivoService Mantenimiento_preventivoService;
	
	@Autowired
	private IReporteService ReporteService;

	
	@GetMapping(value = "/rutinamtto/{id}/{idmtto}")
	public String generarrutina(@PathVariable(value="id") Long id,@PathVariable(value="idmtto") Long idmtto,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) {
		Equipo equipo  = EquipoService.findOne(id);
		System.out.println(Protocolo_preventivoService.protocolobymtto(idmtto).size());
		if(Protocolo_preventivoService.protocolobymtto(idmtto).size()!=0){
			return "redirect:/nuevopreventivo/"+id+"/"+idmtto;
		}
		else {
			List<Protocolo_preventivo> protocolos_preventivo = new ArrayList<Protocolo_preventivo>();
			if(id==540) {
				protocolos_preventivo = Protocolo_preventivoService.protocoloexcepcion(equipo.getTipo_equipo().getId_Tipo_equipo());
			}
			else if(id==414) {
				protocolos_preventivo = Protocolo_preventivoService.protocoloexcepcion(equipo.getTipo_equipo().getId_Tipo_equipo());
				
			}
			else {
				protocolos_preventivo = Protocolo_preventivoService.protocologeneral(equipo.getTipo_equipo().getId_Tipo_equipo());
				
			}
			List<Protocolo_preventivo> nuevos_protocolo_preventivo = new ArrayList<Protocolo_preventivo>();
			for(int proto=0;proto<protocolos_preventivo.size();proto++) {
				Protocolo_preventivo protocolo = new Protocolo_preventivo();
				protocolo.setTipo_equipo(protocolos_preventivo.get(proto).getTipo_equipo());
				protocolo.setMantenimiento_preventivo(Mantenimiento_preventivoService.findOne(idmtto));
				protocolo.setPaso(protocolos_preventivo.get(proto).getPaso());
				protocolo.setCumplimiento(false);

				nuevos_protocolo_preventivo.add(protocolo);
				
			}
			
			model.addAttribute("protocolos",nuevos_protocolo_preventivo);
			map.put("protocolo",nuevos_protocolo_preventivo);
			
			ArrayList<String> materiales = new ArrayList<String>(Arrays.asList(equipo.getTipo_equipo().getMaterial_consumible().split(",")));
			ArrayList<String> herramientas = new ArrayList<String>(Arrays.asList(equipo.getTipo_equipo().getHerramienta().split(",")));
			ArrayList<String> repuestos = new ArrayList<String>(Arrays.asList(equipo.getTipo_equipo().getRepuestos_minimos().split(",")));
			
			model.addAttribute("idmtto",idmtto);
			model.addAttribute("materiales",materiales);
			model.addAttribute("repuestos",repuestos);
			model.addAttribute("herramientas",herramientas);
			model.addAttribute("equipo",equipo);
			
			
			return "rutinamtto";
			
		}
		
	}
	@PostMapping(value = "/rutinamtto/{id}/{idmtto}")
	public String codnsaveprotocol(@PathVariable(value = "id") Long id,@PathVariable(value="idmtto") Long idmtto,		
			@RequestParam(value="cumplimientos")String cumplimientos,
            Model model,
            RedirectAttributes flash,
            @RequestParam(value="codigo",defaultValue = "empty")String codigo,
            SessionStatus status) {
		
		Equipo equipo  = EquipoService.findOne(id);
		System.out.println(codigo);
		System.out.println(equipo.getCodigo());
		
		if(codigo.equals(equipo.getCodigo())) {
			System.out.println(codigo);
			ArrayList<String> listcheck = new ArrayList<String>(Arrays.asList(cumplimientos.split(",")));
			ArrayList<Integer> listchecknum = new ArrayList<Integer>();
			for(int i = 0;i<listcheck.size();i++) {
				listchecknum.add(i,Integer.valueOf(listcheck.get(i)));
			}
			
			
			
			List<Protocolo_preventivo> protocolos_preventivo = new ArrayList<Protocolo_preventivo>();
			if(id==540) {
				protocolos_preventivo = Protocolo_preventivoService.protocoloexcepcion(equipo.getTipo_equipo().getId_Tipo_equipo());
			}
			else if(id==414) {
				protocolos_preventivo = Protocolo_preventivoService.protocoloexcepcion(equipo.getTipo_equipo().getId_Tipo_equipo());
				
			}
			else {
				protocolos_preventivo = Protocolo_preventivoService.protocologeneral(equipo.getTipo_equipo().getId_Tipo_equipo());
				
			}
			
			for(int proto=0;proto<protocolos_preventivo.size();proto++) {
				Protocolo_preventivo protocolo = new Protocolo_preventivo();
				protocolo.setTipo_equipo(protocolos_preventivo.get(proto).getTipo_equipo());
				protocolo.setMantenimiento_preventivo(Mantenimiento_preventivoService.findOne(idmtto));
				protocolo.setPaso(protocolos_preventivo.get(proto).getPaso());
				if(listchecknum.contains(proto)) {
					protocolo.setCumplimiento(true);
				}
				else {
					protocolo.setCumplimiento(false);
				}
				Protocolo_preventivoService.save(protocolo);

			}
			return "redirect:/nuevopreventivo/"+id+"/"+idmtto;
			
		}
		else {
			return "redirect:/rutinamtto/"+id+"/"+idmtto;
		}

	}
	@GetMapping(value="/nuevopreventivo/{id}/{idmtto}")
    public String generarnuevoreportepreventivo(@PathVariable(value="id") Long id,@PathVariable(value="idmtto") Long idmtto,
    								  Map<String,Object>model,Model modaladd,
    								  RedirectAttributes flash) {
    	Reporte reporte = new Reporte();
    	Equipo equipo=null;
    	Mantenimiento_preventivo mantenimiento_preventivo = Mantenimiento_preventivoService.findOne(idmtto);
    	if(id>0){
    		equipo = EquipoService.findOne(id);
        	reporte.setEquipo(equipo);
        	mantenimiento_preventivo.setReporte(reporte);
    		if(equipo==null) {
    			flash.addFlashAttribute("error","El producto no existe en la base de datos");
    			return "redirect:/visualizacionreportes/{id}";
    		}
    		
    	}    	
    	else {
    		flash.addFlashAttribute("error","El ID no puede ser cero");
    		return "redirect:/visualizacionreportes/{id}";
    	}
    	modaladd.addAttribute("serieequipo",equipo.getSerie());
    	modaladd.addAttribute("placa",equipo.getPlaca_inventario());
    	modaladd.addAttribute("servicioequipo",equipo.getServicios());
    	modaladd.addAttribute("idequipo",equipo.getId_Equipo());
    	modaladd.addAttribute("idmtto",idmtto);
    	modaladd.addAttribute("ubicacionequipo",equipo.getUbicacion());
    	modaladd.addAttribute("tipoequipo",equipo.getTipo_equipo());
    	modaladd.addAttribute("nombreequipo",equipo.getNombre_Equipo());
    	modaladd.addAttribute("periodicidad",equipo.getPeriodicidad());
    	model.put("equipo", equipo);
    	modaladd.addAttribute("reporte",reporte);
    	
    	modaladd.addAttribute("numeroreporte",ReporteService.LastIdReporte()+20001);
    	return "nuevopreventivo";
    }
    @PostMapping(value="/nuevopreventivo/{id}/{idmtto}")
    public String guardarnuevoreportepreventivo(@PathVariable Long id,@RequestParam(value="fecha")String fecha,
    		@PathVariable(value="idmtto") Long idmtto,
    		@RequestParam(value="hora_llamado",defaultValue = "00:00")String hora_llamado,
    		@RequestParam(value="hora_inicio",defaultValue = "00:00")String hora_inicio,
    		@RequestParam(value = "hora_finalizacion",defaultValue = "00:00")String hora_finalizacion,
    		@Valid Reporte reporte,
    								  BindingResult result,
    								  Model model,
    								  RedirectAttributes flash,
    								  SessionStatus status) {
    	
    	
    	Equipo equipo = EquipoService.findOne(id);
        
    	LocalDate fechareporte = LocalDate.parse(fecha);
    	Date fechaas = Date.valueOf(fechareporte);
    	reporte.setFecha(fechaas);
    	
    	String hora1 = hora_llamado+":00";    	
    	Time hl = Time.valueOf(hora1);
    	reporte.setHora_llamado(hl);
    	
    	String hora2 = hora_inicio+":00";
    	Time hi = Time.valueOf(hora2);
    	reporte.setHora_inicio(hi);
    	
    	String hora3 = hora_finalizacion+":00";
    	Time hf = Time.valueOf(hora3);
    	reporte.setHora_terminacion(hf);
    	
    	
    	LocalTime hinicio = hi.toLocalTime();
    	LocalTime hfinal = hf.toLocalTime();
    	LocalTime hginalminus = hfinal.minusHours(hinicio.getHour());
    	LocalTime thora = hginalminus.minusMinutes(hinicio.getMinute());
    	Time totalhoras = Time.valueOf(thora);
    	
    	reporte.setTotal_horas(totalhoras);
    	
    	
    	
    	reporte.setEquipo(equipo);
    	
    	reporte.setComodato(false);
    	reporte.setNombre_equipo(equipo.getNombre_Equipo());
    	reporte.setMarca(equipo.getMarca());
    	reporte.setModelo(equipo.getModelo());
    	reporte.setSerie(equipo.getSerie());
    	reporte.setPlaca_inventario(equipo.getPlaca_inventario());
    	reporte.setServicio(equipo.getServicios());
    	reporte.setUbicacion(equipo.getUbicacion());
    	String numr = Long.toString(ReporteService.LastIdReporte()+20001);
    	reporte.setNumero_reporte(numr);
    	reporte.setMtto_propio(true);
    	ReporteService.save(reporte);
    	
    	status.setComplete();
    	
    	Mantenimiento_preventivo mantenimiento_preventivo = Mantenimiento_preventivoService.findOne(idmtto);
    	mantenimiento_preventivo.setReporte(reporte);
    	mantenimiento_preventivo.setFecha_realizacion(fechaas);
    	mantenimiento_preventivo.setTiempo_realizacion(totalhoras);
    	mantenimiento_preventivo.setRealizado(true);
    	Mantenimiento_preventivoService.save(mantenimiento_preventivo);
    	
    	flash.addFlashAttribute("agregado","Reporte agregado correctamente");
    	flash.addFlashAttribute("nombreequipo",equipo.getNombre_Equipo());
    	flash.addFlashAttribute("serieequipo",equipo.getSerie());
    	
    	return "redirect:/mantenimiento";
    	
    }
	
	@GetMapping(value = "/rutinaformatomtto/{id}")
	public String generarformatorutina(@PathVariable(value="id") Long id,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) {
		return "rutinaformatomtto";
	}
	
	
	
}
