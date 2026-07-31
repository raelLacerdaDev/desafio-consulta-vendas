package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportMinDto;
import com.devsuperior.dsmeta.dto.SaleSummaryMinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public ResponseEntity<Page<SaleReportMinDto>> getReport(
			@RequestParam(defaultValue = "") String minDate,
			@RequestParam(defaultValue = "") String maxDate,
			@RequestParam(defaultValue = "") String name,
			Pageable pageable
	) {
		Page<SaleReportMinDto> result = service.searchSalesReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleSummaryMinDto>> getSummary(
			@RequestParam(defaultValue = "") String minDate,
			@RequestParam(defaultValue = "") String maxDate) {
		List<SaleSummaryMinDto> result = service.searchSalesSummaryByDate(minDate, maxDate);
		return ResponseEntity.ok().body(result);
	}
}