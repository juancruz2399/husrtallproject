package com.HUSRTbdBiomedica.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;
import com.HUSRTbdBiomedica.app.entity.Usuario;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IMantenimiento_preventivoService;
import com.HUSRTbdBiomedica.service.IUsuarioService;


@Controller
@RequestMapping
public class CalendarioController {
	
	@Autowired
	private IEquipoService EquipoService;
	
	
	@Autowired
	private IMantenimiento_preventivoService Mantenimiento_preventivoService;
	
	@Autowired
	private IUsuarioService UsuarioService;
	
	@GetMapping("/calendario")
	public String calendarios(Model model,
			RedirectAttributes flash) {
		
		model.addAttribute("eneroprepro",EquipoService.preeneroname());
		model.addAttribute("febreroprepro",EquipoService.prefebreroname());
		model.addAttribute("marzoprepro",EquipoService.premarzoname());
		model.addAttribute("abrilprepro",EquipoService.preabrilname());
		model.addAttribute("mayoprepro",EquipoService.premayoname());
		model.addAttribute("junioprepro",EquipoService.prejunioname());
		model.addAttribute("julioprepro",EquipoService.prejulioname());
		model.addAttribute("agostoprepro",EquipoService.preagostoname());
		model.addAttribute("septiembreprepro",EquipoService.preseptiembrename());
		model.addAttribute("octubreprepro",EquipoService.preoctubrename());
		model.addAttribute("noviembreprepro",EquipoService.prenoviembrename());
		model.addAttribute("diciembreprepro",EquipoService.prediciembrename());
		
		model.addAttribute("eneroprecon",EquipoService.coneneroname());
		model.addAttribute("febreroprecon",EquipoService.confebreroname());
		model.addAttribute("marzoprecon",EquipoService.conmarzoname());
		model.addAttribute("abrilprecon",EquipoService.conabrilname());
		model.addAttribute("mayoprecon",EquipoService.conmayoname());
		model.addAttribute("junioprecon",EquipoService.conjunioname());
		model.addAttribute("julioprecon",EquipoService.conjulioname());
		model.addAttribute("agostoprecon",EquipoService.conagostoname());
		model.addAttribute("septiembreprecon",EquipoService.conseptiembrename());
		model.addAttribute("octubreprecon",EquipoService.conoctubrename());
		model.addAttribute("noviembreprecon",EquipoService.connoviembrename());
		model.addAttribute("diciembreprecon",EquipoService.condiciembrename());
		
		model.addAttribute("eneropregar",EquipoService.gareneroname());
		model.addAttribute("febreropregar",EquipoService.garfebreroname());
		model.addAttribute("marzopregar",EquipoService.garmarzoname());
		model.addAttribute("abrilpregar",EquipoService.garabrilname());
		model.addAttribute("mayopregar",EquipoService.garmayoname());
		model.addAttribute("juniopregar",EquipoService.garjunioname());
		model.addAttribute("juliopregar",EquipoService.garjulioname());
		model.addAttribute("agostopregar",EquipoService.garagostoname());
		model.addAttribute("septiembrepregar",EquipoService.garseptiembrename());
		model.addAttribute("octubrepregar",EquipoService.garoctubrename());
		model.addAttribute("noviembrepregar",EquipoService.garnoviembrename());
		model.addAttribute("diciembrepregar",EquipoService.gardiciembrename());
	
		model.addAttribute("usuarios",UsuarioService.tecnauxbiomedico());
		return "calendario";
	}
	@PostMapping("/programar")
	public String programacion(Model model, RedirectAttributes flash,
			@RequestParam(value = "daterange")String rangofechas,
			@RequestParam(value = "tecnicosalas")String idtecnicosalas) {
		Long tecnicosalas = Long.valueOf(idtecnicosalas);
		String[] fechas = rangofechas.split("-");
		ArrayList<String> fecha12 = new ArrayList<String>(Arrays.asList(fechas));
		String fecha1 = fecha12.get(0).trim();
		String fecha2 = fecha12.get(1).trim();
		ArrayList<String> fecha1div = new ArrayList<String>(Arrays.asList(fecha1.split("/")));
		ArrayList<String> fecha2div = new ArrayList<String>(Arrays.asList(fecha2.split("/")));
		
		int mes1 = Integer.valueOf(fecha1div.get(0));
		int dia1 = Integer.valueOf(fecha1div.get(1));
		int ano1 = Integer.valueOf(fecha1div.get(2));
		
		int mes2 = Integer.valueOf(fecha2div.get(0));
		int dia2 = Integer.valueOf(fecha2div.get(1));
		int ano2 = Integer.valueOf(fecha2div.get(2));
		List<Equipo> equipos = null;
		if(mes1==mes2 && ano1==ano2) {
			ArrayList<Mantenimiento_preventivo> mantenimientos_preventivos = new ArrayList<Mantenimiento_preventivo>();			
			
			if(mes1==1) {
				
				equipos = EquipoService.prevEnero();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						conttecnico+=1;
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
					}
					
				}
				
				
								
			}
			
			else if(mes1==2) {
				equipos = EquipoService.preFebrero();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						conttecnico+=1;
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
					}
					
				}

			}
			else if(mes1==3) {
				equipos = EquipoService.preMarzo();
				
				for(int indice=0;indice<equipos.size();indice++) {
					System.out.println(indice);
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
				
					int diainicial = 0;
					int diafinal = 0;
					if(diasmtto.size()==2) {
						diainicial  = diasmtto.get(0);
						
						diafinal  = diasmtto.get(1);
					}
					else {
						diainicial  = diasmtto.get(0);
						diafinal = diainicial+3;
					}
					
					for(int dia=dia1;dia<dia2+1;dia++) {
						
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
					
							
						}
						
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						conttecnico+=1;
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
					}
					
				}
				
			}
			else if(mes1==4) {
				equipos = EquipoService.preAbril();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						conttecnico+=1;
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
					}
					
				}
				
			}
			else if(mes1==5) {
				equipos = EquipoService.preMayo();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				
			
				int totalmttos = mantenimientos_preventivos.size();				
				
			
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						conttecnico+=1;
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
					}
					
				}
		
				
			}
			else if(mes1==6) {
				equipos = EquipoService.preJunio();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						conttecnico+=1;
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
					}
					
				}
			}
			else if(mes1==7) {
				equipos = EquipoService.preJulio();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						conttecnico+=1;
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
					}
					
				}
			}
			else if(mes1==8) {
				equipos = EquipoService.preAgosto();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						conttecnico+=1;
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
					}
					
				}
			}
			else if(mes1==9) {
				equipos = EquipoService.preSeptiembre();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						conttecnico+=1;
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
					}
					
				}
			}
			else if(mes1==10) {
				equipos = EquipoService.preOctubre();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						conttecnico+=1;
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
					}
					
				}
			}
			else if(mes1==11) {
				equipos = EquipoService.preNoviembre();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						conttecnico+=1;
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
					}
					
				}
			}
			else if(mes1==12) {
				equipos = EquipoService.preDiciembre();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						conttecnico+=1;
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
					}
					
				}
			}
			else {
				equipos = EquipoService.prevEnero();
			}
			
		}
		else {
			//COMPLETAR PARA FECHAS FUERA DEL MISMO MES
			return "redirect:/calendario";
		}
		
		
		return "redirect:/calendario";
	}
	@PostMapping("/calendario")
	public String calendars(Model model,
			RedirectAttributes flash,
			@RequestParam(value = "mesparatodos")int mesprog
			) {
		List<Equipo> prepropios = null;
		List<Equipo> precontrato = null;
		List<Equipo> pregarantia = null;
		int numprepro = 0;
		int numprecon = 0;
		int numprega = 0;
		String Mes = "No se ha seleccionado ningun mes";
		if(mesprog==1) {
			Mes = "Enero";
			numprepro = EquipoService.numprevEnero();
			numprecon = EquipoService.numcontratovEnero();
			numprega = EquipoService.numgarantiavEnero();
			prepropios = EquipoService.prevEnero();
			precontrato = EquipoService.contratovEnero();
			pregarantia = EquipoService.garantiavEnero();
		}
		else if(mesprog==2) {
			Mes = "Febrero";
			numprepro = EquipoService.numpreFebrero();
			numprecon = EquipoService.numcontratoFebrero();
			numprega = EquipoService.numgarantiaFebrero();
			prepropios = EquipoService.preFebrero();
			precontrato = EquipoService.contratoFebrero();
			pregarantia = EquipoService.garantiaFebrero();
			
		}
		else if(mesprog==3) {
			Mes = "Marzo";
			numprepro = EquipoService.numpreMarzo();
			numprecon = EquipoService.numcontratoMarzo();
			numprega = EquipoService.numgarantiaMarzo();
			prepropios = EquipoService.preMarzo();
			precontrato = EquipoService.contratoMarzo();
			pregarantia = EquipoService.garantiaMarzo();		
		}
		else if(mesprog==4) {
			Mes = "Abril";
			numprepro = EquipoService.numpreAbril();
			numprecon = EquipoService.numcontratoAbril();
			numprega = EquipoService.numgarantiaAbril();
			prepropios = EquipoService.preAbril();
			precontrato = EquipoService.contratoAbril();
			pregarantia = EquipoService.garantiaAbril();
		}
		else if(mesprog==5) {
			Mes = "Mayo";
			numprepro = EquipoService.numpreMayo();
			numprecon = EquipoService.numcontratoMayo();
			numprega = EquipoService.numgarantiaMayo();
			prepropios = EquipoService.preMayo();
			precontrato = EquipoService.contratoMayo();
			pregarantia = EquipoService.garantiaMayo();
		}
		else if(mesprog==6) {
			Mes = "Junio";
			numprepro = EquipoService.numpreJunio();
			numprecon = EquipoService.numcontratoJunio();
			numprega = EquipoService.numgarantiaJunio();
			prepropios = EquipoService.preJunio();
			precontrato = EquipoService.contratoJunio();
			pregarantia = EquipoService.garantiaJunio();
		}
		else if(mesprog==7) {
			Mes = "Julio";
			numprepro = EquipoService.numpreJulio();
			numprecon = EquipoService.numcontratoJulio();
			numprega = EquipoService.numgarantiaJulio();
			prepropios = EquipoService.preJulio();
			precontrato = EquipoService.contratoJulio();
			pregarantia = EquipoService.garantiaJulio();
		}
		else if(mesprog==8) {
			Mes = "Agosto";
			numprepro = EquipoService.numpreAgosto();
			numprecon = EquipoService.numcontratoAgosto();
			numprega = EquipoService.numgarantiaAgosto();
			prepropios = EquipoService.preAgosto();
			precontrato = EquipoService.contratoAgosto();
			pregarantia = EquipoService.garantiaAgosto();
					
		}
		else if(mesprog==9) {
			Mes = "Septiembre";
			numprepro = EquipoService.numpreSeptiembre();
			numprecon = EquipoService.numcontratoSeptiembre();
			numprega = EquipoService.numgarantiaSeptiembre();
			prepropios = EquipoService.preSeptiembre();
			precontrato = EquipoService.contratoSeptiembre();
			pregarantia = EquipoService.garantiaSeptiembre();
		}
		else if(mesprog==10) {
			Mes = "Octubre";
			numprepro = EquipoService.numpreOctubre();
			numprecon = EquipoService.numcontratoOctubre();
			numprega = EquipoService.numgarantiaOctubre();
			prepropios = EquipoService.preOctubre();
			precontrato = EquipoService.contratoOctubre();
			pregarantia = EquipoService.garantiaOctubre();
		}
		else if(mesprog==11) {
			Mes = "Noviembre";
			numprepro = EquipoService.numpreNoviembre();
			numprecon = EquipoService.numcontratoNoviembre();
			numprega = EquipoService.numgarantiaNoviembre();
			prepropios = EquipoService.preNoviembre();
			precontrato = EquipoService.contratoNoviembre();
			pregarantia = EquipoService.garantiaNoviembre();
		}
	
		else if(mesprog==12) {
			Mes = "Diciembre";
			numprepro = EquipoService.numpreDiciembre();
			numprecon = EquipoService.numcontratoDiciembre();
			numprega = EquipoService.numgarantiaDiciembre();
			prepropios = EquipoService.preDiciembre();
			precontrato = EquipoService.contratoDiciembre();
			pregarantia = EquipoService.garantiaDiciembre();
		}
		model.addAttribute("Messelect",Mes);
		model.addAttribute("numprepro",numprepro);
		model.addAttribute("numprecon",numprecon);
		model.addAttribute("numprega",numprega);
		model.addAttribute("prepropios",prepropios);
		model.addAttribute("precontrato",precontrato);
		model.addAttribute("pregarantia",pregarantia);
		
		
		model.addAttribute("frebrero",EquipoService.preFebrero());
		
		

		return "calendario";
	}

}
