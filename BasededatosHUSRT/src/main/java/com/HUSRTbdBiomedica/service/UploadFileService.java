package com.HUSRTbdBiomedica.service;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
	
	private String upload_folder = "./src/main/resources/files/";
	
	
	
	public void saveFile(MultipartFile file,Long id) throws IOException{
		
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(upload_folder +id+ file.getOriginalFilename());
			System.out.println(path);
			Files.write(path, bytes);
		}
	}

}
