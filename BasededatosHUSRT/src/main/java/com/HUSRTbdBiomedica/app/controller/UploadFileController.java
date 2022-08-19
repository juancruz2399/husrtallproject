package com.HUSRTbdBiomedica.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.service.IReporteService;
import com.HUSRTbdBiomedica.service.UploadFileService;

@Controller
@RequestMapping
public class UploadFileController {
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private IReporteService ReporteService;
	
	
	@PostMapping(value = "/uploadreport/{id}")
	public String uploadFile(@PathVariable(value="id") Long id,@RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException{
		
		String upload_folder = "./src/main/resources/files/";		
		Reporte reporte = ReporteService.findOne(id);
		reporte.setRutapdf(upload_folder + String.valueOf(id)+ file.getOriginalFilename());
		
		uploadFileService.saveFile(file,id);
		ReporteService.save(reporte);
		
		return "redirect:/visualizacionreportes/"+reporte.getEquipo().getId_Equipo();
		
	}
	
	

}
