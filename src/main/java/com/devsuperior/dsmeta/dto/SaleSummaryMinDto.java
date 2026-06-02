package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSummaryByDateProjection;

public class SaleSummaryMinDto {

    private String name;
    private Double total;

    public SaleSummaryMinDto(String name, Double total) {
        this.name = name;
        this.total = total;
    }


    public SaleSummaryMinDto(SaleSummaryByDateProjection projection) {
        this.name = projection.getName();
        this.total = projection.getTotal();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
