package com.HUSRTbdBiomedica.app.controller;

import java.sql.Date;
import java.time.LocalDate;
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
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IHoja_vidaService;

@Controller
@SessionAttributes("hoja_vida")
@RequestMapping
public class Hoja_vidaController {
	@Autowired
	private IHoja_vidaService Hoja_vidaService;
	
	@Autowired
	private IEquipoService EquipoService;
	
	@GetMapping(value = "/matrizhv/{id}")
    public String showhvequipo(@PathVariable(value = "id") Long id,
                      Model model,
                      RedirectAttributes flash) {
		
    	Equipo equipo = EquipoService.findOne(id);
    	
    	
    	model.addAttribute("idequipo",equipo.getId_Equipo());
    	model.addAttribute("nombreequipo",equipo.getNombre_Equipo());
    	model.addAttribute("marcaequipo",equipo.getMarca());
    	model.addAttribute("modeloequipo",equipo.getModelo());
    	model.addAttribute("serieequipo",equipo.getSerie());
    	model.addAttribute("servicioequipo",equipo.getServicios());
    	model.addAttribute("ubicacionequipo",equipo.getUbicacion());
    	model.addAttribute("diasmtto",equipo.getDias_mantenimiento());
    	model.addAttribute("mesesmtto",equipo.getMeses_mantenimiento());
    	model.addAttribute("anomtto",equipo.getAno_mantenimiento());
    	model.addAttribute("anoingresoequipo",equipo.getAno_ingreso());
    	model.addAttribute("placainventario",equipo.getPlaca_inventario());
    	
    	
    	try {
    		Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(id);
    		model.addAttribute("hoja_vidaequipo",hoja_vida);
    	}
    	catch(Exception e) {
    		System.out.println("hola");
    		return "redirect:/listarhvrepeat/"+equipo.getId_Equipo();
    	}
    	
    	
      
        return "matrizhv";
    }
	@GetMapping(value = "/listarhvrepeat/{id}")
	public String gotodeletehv(@PathVariable(value = "id") Long id,
            Model model,            
            RedirectAttributes flash) {
		
		List<Hoja_vida> hojas_vida = Hoja_vidaService.ObtainallHV();
		
		model.addAttribute("todashv",hojas_vida);
		model.addAttribute("equipo",EquipoService.findOne(id));
		return "listarhvrepeat";
	}
	
	@GetMapping("/eliminarHV/{id}")
	public String eliminarHV(@PathVariable(value = "id") Long id,
            RedirectAttributes flash) {
		Hoja_vida hoja_vida = Hoja_vidaService.findOne(id);
		hoja_vida.setEquipo(null);
		if(hoja_vida != null) {
			Hoja_vidaService.delete(id);
			flash.addFlashAttribute("success","Hoja de vida eliminada con éxito");	           
			return "redirect:/producto";
		}
		return "redirect:/producto";
	}
	
	@GetMapping("/nuevoequipo/{id}")
	public String crearnuevoequipo(@PathVariable(value = "id") Long id,
            Model model,Map<String, Object> map,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
		Hoja_vida hoja_vida = new Hoja_vida();
		hoja_vida.setEquipo(equipo);
		map.put("hoja_vida", hoja_vida);
		model.addAttribute("equipo",equipo);
		
		
		return "nuevoequipo";
	}
	@GetMapping("/editarequipo/{id}")
	public String editarhv(@PathVariable(value="id") Long id,
			Model model,Map<String, Object> map,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
		Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(id);
		map.put("hoja_vida", hoja_vida);
		model.addAttribute("equipo",equipo);
		return  "nuevoequipo";
	}
	@PostMapping("/nuevoequipo/{id}")
	public String guardarnuevahv(@PathVariable(value = "id") Long id,@Valid Hoja_vida hoja_vida,            
            Model model,
            RedirectAttributes flash,
            @RequestParam(value="fecha_compra",defaultValue = "2022-01-01")String fecha_compra,
            @RequestParam(value="fecha_instalacion",defaultValue = "2022-01-01")String fecha_instalacion,
            @RequestParam(value="fecha_inicio_operacion",defaultValue = "2022-01-01")String fecha_inicio_operacion,
            @RequestParam(value="fecha_vencimiento",defaultValue = "2022-01-01")String fecha_vencimiento,
            @RequestParam(value="foto",defaultValue = "empty")String foto,
            SessionStatus status) {
		
		
		
		LocalDate fechacompra = LocalDate.parse(fecha_compra);
    	Date fechac = Date.valueOf(fechacompra);
    	LocalDate fechainstalacion = LocalDate.parse(fecha_instalacion);
    	Date fechai = Date.valueOf(fechainstalacion);
    	LocalDate fechainicio = LocalDate.parse(fecha_inicio_operacion);
    	Date fechaop = Date.valueOf(fechainicio);
    	LocalDate fechavencimiento= LocalDate.parse(fecha_vencimiento);
    	Date fechav = Date.valueOf(fechavencimiento);
    	
    	hoja_vida.setFecha_compra(fechac);
    	hoja_vida.setFecha_instalacion(fechai);
    	hoja_vida.setFecha_iniciooperacion(fechaop);
    	hoja_vida.setFecha_vencimientogarantia(fechav);
    	
    	
		hoja_vida.setEquipo(EquipoService.findOne(id));		
		String Fotohv = "/images/"+String.valueOf(foto);
		hoja_vida.setFoto(Fotohv);
		Hoja_vidaService.save(hoja_vida);
		
        status.setComplete();
        flash.addFlashAttribute("success","guardado");
        return "redirect:/matrizhv/"+hoja_vida.getEquipo().getId_Equipo();

	}
	
	@GetMapping(value="/formatohojadevida/{id}")
	public String showhvformatoequipo(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
		
		
		model.addAttribute("period",equipo.getPeriodicidad());
    	model.addAttribute("idequipo",equipo.getId_Equipo());
    	model.addAttribute("nombreequipo",equipo.getNombre_Equipo());
    	model.addAttribute("marcaequipo",equipo.getMarca());
    	model.addAttribute("modeloequipo",equipo.getModelo());
    	model.addAttribute("serieequipo",equipo.getSerie());
    	model.addAttribute("servicioequipo",equipo.getServicios());
    	model.addAttribute("ubicacionequipo",equipo.getUbicacion());
    	model.addAttribute("diasmtto",equipo.getDias_mantenimiento());
    	model.addAttribute("mesesmtto",equipo.getMeses_mantenimiento());
    	model.addAttribute("anomtto",equipo.getAno_mantenimiento());
    	model.addAttribute("anoingresoequipo",equipo.getAno_ingreso());
    	model.addAttribute("placainventario",equipo.getPlaca_inventario());
    	model.addAttribute("contratoequipo",Hoja_vidaService.findHVbyEquipo(id).getContrato());
    	model.addAttribute("hoja_vidaequipo",Hoja_vidaService.findHVbyEquipo(id));
		return "formatohojadevida";
	}
	
	@GetMapping(value ="/hvdatosequipo/{id}")
	public String showhvdatosgenerales(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
		
    	model.addAttribute("idequipo",equipo.getId_Equipo());
    	model.addAttribute("nombreequipo",equipo.getNombre_Equipo());
    	model.addAttribute("ubicacionequipo",equipo.getUbicacion());
    	model.addAttribute("marcaequipo",equipo.getMarca());
    	model.addAttribute("serieequipo",equipo.getSerie());
    	model.addAttribute("modeloequipo",equipo.getModelo());
    	model.addAttribute("servicioequipo",equipo.getServicios());
    	model.addAttribute("placainventario",equipo.getPlaca_inventario());
		model.addAttribute("hoja_vidadg",Hoja_vidaService.findHVbyEquipo(id));
		return "hvdatosequipo";
	
	}
	
	@GetMapping(value ="/hvdatoscompra/{id}")
	public String showhvdatoscompra(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
    	model.addAttribute("idequipo",equipo.getId_Equipo());
		model.addAttribute("hoja_vidadc",Hoja_vidaService.findHVbyEquipo(id));
		return "hvdatoscompra";
	
	}
	@GetMapping(value="/hvdatostecnicos/{id}")
	public String showhvdatostecnicos(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
    	model.addAttribute("idequipo",equipo.getId_Equipo());
		model.addAttribute("hoja_vidadt",Hoja_vidaService.findHVbyEquipo(id));
		return "hvdatostecnicos";
		
	}
	@GetMapping(value="/hvdatosregulatorios/{id}")
	public String showhvdatosregulatorios(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
    	model.addAttribute("idequipo",equipo.getId_Equipo());
		model.addAttribute("hoja_vidadr",Hoja_vidaService.findHVbyEquipo(id));
		return "hvdatosregulatorios";
		
	}
	@GetMapping(value="/hvclasificacionequipo/{id}")
	public String showhvclasificacion(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
    	model.addAttribute("idequipo",equipo.getId_Equipo());
		model.addAttribute("hoja_vidac",Hoja_vidaService.findHVbyEquipo(id));
		return "hvclasificacionequipo";
		
	}
	@GetMapping(value="/hvmantenimientoequipo/{id}")
	public String showhvmantenimiento(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
		model.addAttribute("period",equipo.getPeriodicidad());
    	model.addAttribute("idequipo",equipo.getId_Equipo());
		model.addAttribute("hoja_vidam",Hoja_vidaService.findHVbyEquipo(id));
		return "hvmantenimientoequipo";
		
	}
	@GetMapping(value="/hvaccesorios/{id}")
	public String showhvaccesorios(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
    	model.addAttribute("idequipo",equipo.getId_Equipo());
		model.addAttribute("hoja_vidaa",Hoja_vidaService.findHVbyEquipo(id));
		return "hvaccesorios";
		
	}
	

}
