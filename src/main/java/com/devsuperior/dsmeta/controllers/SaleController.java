package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportMinDto;
import com.devsuperior.dsmeta.dto.SaleSummaryMinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<?> getReport(
			@RequestParam(defaultValue = "2025-01-01") String minDate,
			@RequestParam(defaultValue = "2025-12-31") String maxDate,
			@RequestParam(defaultValue = "") String name
			) {
		List<SaleReportMinDto> result = service.searchSalesReport(minDate, maxDate, name);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleSummaryMinDto>> getSummary(@RequestParam(defaultValue = "2025-01-01") String minDate, @RequestParam(defaultValue = "2025-12-31") String maxDate) {
		List<SaleSummaryMinDto> result = service.searchSalesSummaryByDate(minDate, maxDate);
		return ResponseEntity.ok().body(result);
	}
}
