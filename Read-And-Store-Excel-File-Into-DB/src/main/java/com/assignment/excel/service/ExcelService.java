package com.assignment.excel.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.excel.helper.ExcelHelper;
import com.assignment.excel.model.Student;
import com.assignment.excel.repository.StudentRepository;

@Service
public class ExcelService {
  @Autowired
  StudentRepository repository;

  public void save(MultipartFile file) {
	  System.out.println("Inside Save method");
    try {
    	System.out.println("My tutorial va;ue  is---"+ ExcelHelper.excelToTutorials(file.getInputStream()));
      List<Student> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<Student> tutorials = repository.findAll();

    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
    return in;
  }

  public List<Student> getAllTutorials() {
    return repository.findAll();
  }
}
