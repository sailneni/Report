package com.sai.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sai.entity.CitizenPlan;
import com.sai.repo.CitizenPlanRepository;
import com.sai.request.SearchRequest;
import com.sai.util.EmailUtils;
import com.sai.util.ExcelGenerator;
import com.sai.util.PdfGenerator;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public List<String> getPlanNames() {
		
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		
		CitizenPlan entity=new CitizenPlan();
		
		if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		if(null!=request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		
		if(null!=request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate=request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate= LocalDate.parse(startDate, formatter);
			
			
			entity.setPlanStartDate(localDate);
		}
		
		if(null!=request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate=request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate= LocalDate.parse(endDate, formatter);
			
			
			entity.setPlanEndDate(localDate);
		}
		
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		//List<CitizenPlan> plans= planRepo.findAll();
		File file= new File("Plans.xls");
		excelGenerator.generate(response, file);
		String subject= "test mail";
		String body="<h1>Test Mail Body</h1>";
		String to= "aillneni.teja@gmail.com";
		
		emailUtils.sendEmail(subject, body, to, file);
		file.delete();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		List<CitizenPlan> plans= planRepo.findAll();
		File file= new File("Plans.pdf");
		pdfGenerator.generate(response,plans, file);
		String subject= "test mail";
		String body="<h1>Test Mail Body</h1>";
		String to= "aillneni.teja@gmail.com";
		emailUtils.sendEmail(subject, body, to, file);
		
		file.delete();
		
		return true;
	}

}
