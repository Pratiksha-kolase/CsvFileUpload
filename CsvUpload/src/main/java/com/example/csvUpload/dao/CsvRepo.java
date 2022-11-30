package com.example.csvUpload.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.csvUpload.model.CsvModel;

public interface CsvRepo extends JpaRepository<CsvModel, String>{

}
