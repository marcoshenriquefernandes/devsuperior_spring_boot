package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SummarySumDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<Page<SalesReportDTO>> getReport(@RequestParam(name = "minDate", required = false) String minDate,
														  @RequestParam(name = "maxDate", required = false) String maxDate,
														  @RequestParam(name = "name", defaultValue = "") String name,
														  Pageable pageable) {
		Page<SalesReportDTO> dto = service.searchSalesReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummarySumDTO>> getSummary(@RequestParam(name = "minDate", required = false) String minDate,
													      @RequestParam(name = "maxDate", required = false) String maxDate) {
		List<SummarySumDTO> dto = service.searchSummarySum(minDate, maxDate);
		return ResponseEntity.ok(dto);
	}
}
