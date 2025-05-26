package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SummarySumDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SummarySumDTO> searchSummarySum(String minDate, String maxDate) {
		LocalDate minDateFormatter = dateFormatter(minDate);
		LocalDate maxDateFormatter = dateFormatter(maxDate);

		if(maxDateFormatter == null)
			maxDateFormatter = LocalDate.now();

		if(minDateFormatter == null)
			minDateFormatter = maxDateFormatter.minusYears(1L);

		return repository.searchSummarySum(minDateFormatter, maxDateFormatter);
	}

	public Page<SalesReportDTO> searchSalesReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate minDateFormatter = dateFormatter(minDate);
		LocalDate maxDateFormatter = dateFormatter(maxDate);

		if(maxDateFormatter == null)
			maxDateFormatter = LocalDate.now();

		if(minDateFormatter == null)
			minDateFormatter = maxDateFormatter.minusYears(1L);

		return repository.searchSalesReport(minDateFormatter, maxDateFormatter, name, pageable);
	}

	private LocalDate dateFormatter(String date){
		return (date != null && !date.isEmpty()) ? LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
	}
}
