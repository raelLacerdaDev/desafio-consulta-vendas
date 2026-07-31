package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportMinDto;
import com.devsuperior.dsmeta.dto.SaleSummaryMinDto;
import com.devsuperior.dsmeta.projections.SaleReportByDateProjection;
import com.devsuperior.dsmeta.projections.SaleSummaryByDateProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<SaleSummaryMinDto> searchSalesSummaryByDate(String minDate, String maxDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate end = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate start = minDate.equals("") ? end.minusYears(1L) : LocalDate.parse(minDate);

		List<SaleSummaryByDateProjection> result = repository.saleSummaryByDate(start, end);
		return result.stream().map(SaleSummaryMinDto::new).toList();
	}

	@Transactional(readOnly = true)
	public Page<SaleReportMinDto> searchSalesReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate end = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate start = minDate.equals("") ? end.minusYears(1L) : LocalDate.parse(minDate);

		Page<SaleReportByDateProjection> result = repository.saleReport(start, end, name, pageable);
		return result.map(SaleReportMinDto::new);
	}
}
