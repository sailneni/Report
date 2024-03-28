package com.sai.service;

import java.util.List;

import com.sai.entity.CitizenPlan;
import com.sai.request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;
}
