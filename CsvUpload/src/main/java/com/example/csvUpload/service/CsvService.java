package com.example.csvUpload.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.csvUpload.model.CsvModel;
public interface CsvService {

	ResponseEntity<String> upload(MultipartFile multipartFile) throws IOException;


}
