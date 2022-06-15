package com.assignment.excel.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.excel.model.Student;

public class ExcelHelper {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = {"Name", "Class", "Subject", "Marks" };
  static String SHEET = "Sheet1";

  public static boolean hasExcelFormat(MultipartFile file) {
 System.out.println("inside MultipartFile");
    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static ByteArrayInputStream tutorialsToExcel(List<Student> students) {

    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
      Sheet sheet = workbook.createSheet(SHEET);

      // Header
      Row headerRow = sheet.createRow(0);

      for (int col = 0; col < HEADERs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(HEADERs[col]);
      }

      int rowIdx = 1;
      for (Student st : students) {
        Row row = sheet.createRow(rowIdx++);
        System.out.println("my student obj value is--"+st.getName());
        row.createCell(0).setCellValue(st.getName());
        row.createCell(1).setCellValue(st.getClassName());
        row.createCell(2).setCellValue(st.getSubject());
        row.createCell(3).setCellValue(st.getMarks());
      }

      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    }
  }

  public static List<Student> excelToTutorials(InputStream is) {
    try {
      Workbook workbook = new XSSFWorkbook(is);
      System.out.println("Inside excelToTutorials method");
      Sheet sheet = workbook.getSheet(SHEET);
      Iterator<Row> rows = sheet.iterator();
      
      List<Student> students = new ArrayList<Student>();

      int rowNumber = 0;
      while (rows.hasNext()) {
        Row currentRow = rows.next();
         System.out.println("my rowNumber--"+rowNumber);
        // skip header
        if (rowNumber == 0) {
          rowNumber++;
          continue;
        }

        Iterator<Cell> cellsInRow = currentRow.iterator();

        Student student = new Student();
     
        
        while (cellsInRow.hasNext()) {
          Cell currentCell = cellsInRow.next();
         System.out.println("my value---"+currentCell.toString());
         
        	// System.out.println("My cell value is---"+currentCell );
         System.out.println("Row Index" +currentCell.getRowIndex());
         System.out.println("Col Index" +currentCell.getColumnIndex());
          
        	  if(currentCell.getColumnIndex() == 0)
        	  student.setName(currentCell.getStringCellValue());
            
            if(currentCell.getColumnIndex() == 1)
        	  student.setClassName((int) currentCell.getNumericCellValue());
         	  if(currentCell.getColumnIndex() == 2)
        	  student.setSubject(currentCell.getStringCellValue());
            if(currentCell.getColumnIndex() == 3)
            student.setMarks((int) currentCell.getNumericCellValue());
           
          

          
        }

        students.add(student);
      }

      workbook.close();

      return students;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    }
  }
}
