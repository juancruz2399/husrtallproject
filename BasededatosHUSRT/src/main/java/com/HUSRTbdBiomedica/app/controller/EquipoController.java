package com.HUSRTbdBiomedica.app.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.app.entity.Hospital;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.app.entity.Servicio;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IHospital_Service;
import com.HUSRTbdBiomedica.service.IServicioService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;

@Controller
@SessionAttributes("equipo")
@RequestMapping
public class EquipoController {
	@Autowired
	private IEquipoService EquipoService;
	
	@Autowired
	private ITipo_equipoService Tipo_equipoService;
	
	@Autowired
	private IHospital_Service HospitalService;
	
	@Autowired
	private IServicioService ServicioService;
	
	@GetMapping("/producto")
	public String Dashboard(Model model) {
		return "producto";
	}
	@GetMapping("/todoslosequipos")
	public String ListarTipo_equipos(Model model) {
		model.addAttribute("tipo_equiporrios",Tipo_equipoService.ListTipo_equipo());
		return "todoslosequipos";
	}
	
	@GetMapping("/semaforizacion")
	public String Semaforizacion(Model model) {
		return "semaforizacion";
	}
	
	
	
	@GetMapping("/trimestral")
	public String cuadrarTodos(Model model) {
		model.addAttribute("numerodeequipost",EquipoService.ContarEquiposTrimestral());
	
		model.addAttribute("tiposequipostrimestrales",Tipo_equipoService.findTipo_equipobyPeriodicidad(3));
		return "trimestral";
	}
	
	@GetMapping("/cuatrimestral")
	public String cuadrarCuatrimestrales(Model model) {
		model.addAttribute("numerodeequiposc",EquipoService.ContarEquiposCuatrimestral());
		model.addAttribute("tiposequiposcuatrimestrales",Tipo_equipoService.findTipo_equipobyPeriodicidad(4));
		return "cuatrimestral";
	}
	@GetMapping("/price")
	public String cuadrarSemestrales(Model model) {
		model.addAttribute("numerodeequiposs",EquipoService.ContarEquiposSemestral());
		model.addAttribute("tiposequipossemestrales",Tipo_equipoService.findTipo_equipobyPeriodicidad(2));
		return "price";
	}
	@GetMapping("/anual")
	public String cuadrarAnual(Model model) {
		model.addAttribute("numerodeequiposa",EquipoService.ContarEquiposAnual());
		model.addAttribute("tiposequiposanuales",Tipo_equipoService.findTipo_equipobyPeriodicidad(1));
		return "anual";
	}
	@GetMapping("/prueba/{id}")
	public void MostrarContadorTipo(@PathVariable(value="id")Long id,Model model) {
		model.addAttribute("numeroportipobyp",Tipo_equipoService.countEbyTipoEquipobyP(2, id));
	}
	
	@PostMapping("/buscadoporserie")
	public String buscadoporserio(@RequestParam(value="mySerie",defaultValue = "BBF05937")String serie,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findequipobySerie(serie);
		if(equipo!=null) {
			model.addAttribute("nombrelineaequipo",equipo.getNombre_Equipo());
			model.addAttribute("equiposerie",equipo);
			return "buscadoporserie";
		}
		else {
			return "redirect:/periodicidad";
		}
		
	}
	@PostMapping("/nuevoequipomtto")
	public String crearnuevoequipomtto(@RequestParam(value="myTipo",defaultValue = "BALANZA")String name,
            Model model,Map<String,Object>map,
            RedirectAttributes flash){
		Equipo equipo = new Equipo();		
		Tipo_equipo tipo = Tipo_equipoService.findbyName(name);
		Hospital hospital = HospitalService.findOne(1L);
		if(tipo==null) {
			return  "redirect:/usuarios";
		}
		else {
			
			equipo.setTipo_equipo(tipo);
			equipo.setHospital(hospital);
			List<String> nombres = new ArrayList<String>();
			List<Servicio> servicios =  ServicioService.ListServicio();
			for(int i = 0; i<servicios.size();i++) {
				nombres.add(servicios.get(i).getNombre_servicio());
			}
			
			model.addAttribute("servicios",nombres);
			map.put("equipo", equipo);
			return "nuevoequiposinhv";
		}
		
	
	}
	@PostMapping("/editarportipo")
	public String editarequiposbytipo(@RequestParam(value="myTipoedit",defaultValue = "BALANZA")String name,
            Model model,Map<String,Object>map,
            RedirectAttributes flash){
		Tipo_equipo tipo = Tipo_equipoService.findbyName(name);
		if(tipo==null) {
			return  "redirect:/usuarios";
		}
		else {
			model.addAttribute("tipo", tipo);
			model.addAttribute("equipos",Tipo_equipoService.findEquiposbyTipoEquipo(tipo.getId_Tipo_equipo()));
			return "listedit";
		}
		
	}
	
	
	@GetMapping("/listinactivos")
	public String listarinactivos(
            Model model,
            RedirectAttributes flash) {
		model.addAttribute("equipos",EquipoService.findEquiposInactivos());
		return "listinactivos";
	}
	
	@GetMapping("/editarequipomtto/{id}")
	public String editarequipo(@PathVariable(value="id") Long id,
			Model model,Map<String, Object> map,
            RedirectAttributes flash) {
		Equipo equipo  = EquipoService.findOne(id);
		List<String> nombres = new ArrayList<String>();
		List<Servicio> servicios =  ServicioService.ListServicio();
		for(int i = 0; i<servicios.size();i++) {
			nombres.add(servicios.get(i).getNombre_servicio());
		}
		
		model.addAttribute("servicios",nombres);
		model.addAttribute("meses",equipo.getMeses_mantenimiento());
		model.addAttribute("dias",equipo.getDias_mantenimiento());
		map.put("equipo", equipo);
		
		
		return "nuevoequiposinhv";
	}
	@PostMapping("/crearnuevoequipo/{id}")
	public String guardarnuevoequipomtto(@PathVariable(value="id")Long id,
									  @Valid Equipo equipo,
    								  BindingResult result,
    								  Model model,
    								  RedirectAttributes flash,
    								  SessionStatus status,
    								  @RequestParam(value="datetimes",defaultValue = "NR")String dias,
    								  @RequestParam(value="metodoMesesPreventivo",defaultValue = "NR")String meses,
    								  @RequestParam(value = "myService",defaultValue = "HUSRT")String servicio,
    								  @RequestParam(value = "tipoequipo",defaultValue = "BALANZA")String tipo)  {
		
		Tipo_equipo tipo_equipo = Tipo_equipoService.findOne(id);
		equipo.setTipo_equipo(tipo_equipo);
		equipo.setHospital(HospitalService.findOne(1L));
		equipo.setServicio(ServicioService.findbyName(servicio));
		equipo.setServicios(servicio);
		equipo.setMeses_mantenimiento(meses);
		ArrayList<String> mescut = new ArrayList<String>(Arrays.asList(meses.split(",")));
		ArrayList<String> diascut = new ArrayList<String>(Arrays.asList(dias.split(",")));
		List<String> diasasign = new ArrayList<String>();
		
		for(int mes = 0; mes<mescut.size();mes++) {
			System.out.println(mescut.get(mes));
			if(mescut.get(mes).equals("ENERO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(0).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))+'-'+String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			}
			else if(mescut.get(mes).equals("FEBRERO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(1).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))+'-'+String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			}
			else if(mescut.get(mes).equals("MARZO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(2).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))+'-'+String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));			
			}
			else if(mescut.get(mes).equals("ABRIL")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(3).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))+'-'+String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			}
			else if(mescut.get(mes).equals("MAYO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(4).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))+'-'+String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			}
			else if(mescut.get(mes).equals("JUNIO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(5).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))+'-'+String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			}
			else if(mescut.get(mes).equals("JULIO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(6).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))+'-'+String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			}
			else if(mescut.get(mes).equals("AGOSTO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(7).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))+'-'+String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			}
			else if(mescut.get(mes).equals("SEPTIEMBRE")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(8).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))+'-'+String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			}
			else if(mescut.get(mes).equals("OCTUBRE")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(9).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))+'-'+String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			}
			else if(mescut.get(mes).equals("NOVIEMBRE")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(10).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))+'-'+String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			}
			else if(mescut.get(mes).equals("DICIEMBRE")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(11).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))+'-'+String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));			
			}
		}
		String diasupload = new String();
		for(int diasd = 0;diasd<diasasign.size();diasd++) {
			if(diasd==diasasign.size()-1) {
				diasupload+=diasasign.get(diasd);
			}
			else {
				diasupload+=diasasign.get(diasd)+',';
			}
		}
		equipo.setDias_mantenimiento(diasupload);
		EquipoService.save(equipo);
		return "redirect:/visualizaciontipoequipo/"+tipo_equipo.getId_Tipo_equipo();
		
	}
	
	
}
