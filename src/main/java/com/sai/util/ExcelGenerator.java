package com.sai.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sai.entity.CitizenPlan;
import com.sai.repo.CitizenPlanRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {
	
	@Autowired
	private CitizenPlanRepository planRepo;
	
	public void generate(HttpServletResponse response, File file) throws Exception {
		List<CitizenPlan> records=planRepo.findAll();
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet=workbook.createSheet("Plans_Data");
		Row headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amount");
		
		int dataRowIndex =1;
		
		for(CitizenPlan plan: records) {
			Row dataRow=sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			
			if(null!=plan.getPlanStartDate()) {
				dataRow.createCell(4).setCellValue(plan.getPlanStartDate()+"");
			}else {
				dataRow.createCell(4).setCellValue("N/A");
			}
			
			if(null!=plan.getPlanEndDate()) {
				dataRow.createCell(5).setCellValue(plan.getPlanEndDate()+"");
			}else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			
			if(null!=plan.getBenifitAmount()) {
				dataRow.createCell(6).setCellValue(plan.getBenifitAmount());
			}else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			
			dataRowIndex++;
		}
		
		
		  FileOutputStream fos= new FileOutputStream(file);
		  workbook.write(fos); 
		  fos.close();
		 
		
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
	}

}
