package com.example.csvUpload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.csvUpload.model.CsvModel;
import com.example.csvUpload.service.CsvService;

@RestController

public class CsvController {
	@Autowired
	private CsvService csvService;
	
	@PostMapping("/uploadFile")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
		return csvService.upload(multipartFile);
	}
	

}
