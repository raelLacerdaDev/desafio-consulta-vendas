package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleReportByDateProjection;

import java.time.LocalDate;

public class SaleReportMinDto {
    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;


    public SaleReportMinDto(SaleReportByDateProjection projection) {
        this.id = projection.getId();
        this.date = projection.getDate();
        this.amount = projection.getAmount();
        this.sellerName = projection.getSellerName();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }


}
