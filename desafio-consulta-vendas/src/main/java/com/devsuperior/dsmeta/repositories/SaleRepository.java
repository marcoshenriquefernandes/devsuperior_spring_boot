package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SummarySumDTO;
import com.devsuperior.dsmeta.entities.Sale;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SummarySumDTO(sale.seller.name, SUM(sale.amount)) " +
           "  FROM Sale sale " +
           " WHERE sale.date BETWEEN :minDate AND :maxDate " +
           " GROUP BY sale.seller.name")
    List<SummarySumDTO> searchSummarySum(LocalDate minDate, LocalDate maxDate);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SalesReportDTO(sale.id, sale.date, sale.amount, sale.seller.name) " +
           "  FROM Sale sale " +
           " WHERE sale.date BETWEEN :minDate AND :maxDate " +
           "   AND upper(sale.seller.name) like upper(concat('%', :name, '%'))")
    Page<SalesReportDTO> searchSalesReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

}
