package com.devsuperior.dsmeta.services;

import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSummaryMinDto;
import com.devsuperior.dsmeta.projections.SaleSummaryByDateProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

import javax.transaction.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Transactional
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional
	public List<SaleSummaryMinDto> searchSalesSummaryByDate(String start, String end) {
		List<SaleSummaryByDateProjection> result = repository.saleSummaryByDate(start, end);
		return result.stream().map(SaleSummaryMinDto::new).toList();
	}

}
