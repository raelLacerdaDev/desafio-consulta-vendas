package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SaleSummaryByDateProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query(
            nativeQuery = true,
            value = "SELECT SE.NAME, ROUND(SUM(SA.AMOUNT),1) AS TOTAL FROM TB_SELLER AS SE " +
                    "INNER JOIN TB_SALES AS SA ON SA.SELLER_ID = SE.ID " +
                    "WHERE SA.DATE BETWEEN :start AND :end " +
                    "GROUP BY SE.ID " +
                    "ORDER BY SE.NAME"
    )
    List<SaleSummaryByDateProjection> saleSummaryByDate(String start, String end);


}
