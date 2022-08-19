package com.HUSRTbdBiomedica.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Servicio;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IHoja_vidaService;
import com.HUSRTbdBiomedica.service.IServicioService;

@Controller
@SessionAttributes("servicio")
@RequestMapping
public class ServicioController {

	@Autowired
	private IServicioService ServicioService;
	
	@Autowired
	private IEquipoService EquipoService;
	
	@Autowired
	private IHoja_vidaService Hoja_vidaService;
	
	@GetMapping("/periodicidad")
	public String ReportesporPeriodicidad(Model model) {
		model.addAttribute("listaseries",EquipoService.listarseries());
		
		return "periodicidad";
	}
	@GetMapping("/clasificacionDHServicio")
	public String ListServicios(Model model) {
		
		model.addAttribute("servicios",ServicioService.ListServicio());
		return "clasificacionDHServicio";
	}
	@GetMapping("/servicio/{id}")
	public String ServicioEquipos(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
		List<Equipo> equipos = ServicioService.findEquiposbyServicio(id);
		List<Hoja_vida> hoja_vida = new ArrayList<Hoja_vida>();
		for(int eq = 0;eq<equipos.size();eq++) {
			hoja_vida.add(Hoja_vidaService.findHVbyEquipo(equipos.get(eq).getId_Equipo()));
		}
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",equipos);
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicio(id));
        model.addAttribute("hojas_vida",hoja_vida);
		
		return "equiposservicio";
	}
	@GetMapping("/todoslosservicios")
	public String listarServicios(Model model) {
		model.addAttribute("servicios",ServicioService.ListServicio());
		return "todoslosservicios";
	}
	@GetMapping("/serviciostrimestrales")
	public String listarServicioscuatri(
			 Model model,
			 RedirectAttributes flash) {
		model.addAttribute("servicios",ServicioService.findServicebyp(3));	
		return "serviciostrimestrales";
	}
	@GetMapping("/servicioscuatrimestrales")
	public String listarServiciostri(
			 Model model,
			 RedirectAttributes flash) {
		model.addAttribute("servicios",ServicioService.findServicebyp(4));
		return "servicioscuatrimestrales";
	}
	@GetMapping("/serviciossemestrales")
	public String listarServiciossem(
			 Model model,
			 RedirectAttributes flash) {
		model.addAttribute("servicios",ServicioService.findServicebyp(2));
		return "serviciossemestrales";
	}
	@GetMapping("/serviciosanuales")
	public String listarServiciosan(
			 Model model,
			 RedirectAttributes flash) {
		model.addAttribute("servicios",ServicioService.findServicebyp(1));
		return "serviciosanuales";
	}
	
	@GetMapping(value = "/visualizacionequiposservicio/{id}")
    public String showequiposbyservicio(@PathVariable(value = "id") Long id,
                      Model model,
                      RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",ServicioService.findEquiposbyServicio(id));
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicio(id));
    	
        return "visualizacionequiposservicio";
    }
	
	@GetMapping(value = "/visualizacionservicioanual/{id}")
    public String showequiposbyservicioanual(@PathVariable(value = "id") Long id,
                      Model model,
                      RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",ServicioService.findEquiposbyServicioanuales(id));
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicionP(1, id));
        return "visualizacionservicioanual";
    }
	
	@GetMapping(value="/visualizacionserviciosemestral/{id}")
	public String showequiposbyserviciosemestral(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",ServicioService.findEquiposbyServiciosemestrales(id));
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicionP(2, id));
		return "visualizacionserviciosemestral";
	}
	@GetMapping(value="/visualizacionserviciocuatrimestral/{id}")
	public String showequiposbyserviciocuatrimestral(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",ServicioService.findEquiposbyServiciocuatrimestrales(id));
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicionP(4, id));
		return "visualizacionserviciocuatrimestral";
	}
	@GetMapping(value="/visualizacionserviciotrimestral/{id}")
	public String showequiposbyserviciotrimestral(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",ServicioService.findEquiposbyServiciotrimestrales(id));
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicionP(3, id));
		return "visualizacionserviciotrimestral";
	}
}
