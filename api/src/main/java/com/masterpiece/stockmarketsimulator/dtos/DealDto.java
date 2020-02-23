package com.masterpiece.stockmarketsimulator.dtos;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class DealDto {

    @NotEmpty
    private String companyName;

    @NotEmpty
    private Long quantity;

    @NotEmpty
    private LocalDate date;


    @NotEmpty
    private Long unityPrice;

    public Long getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(Long unityPrice) {
        this.unityPrice = unityPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
