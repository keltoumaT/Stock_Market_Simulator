package com.masterpiece.stockmarketsimulator.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DealDto {

    @NotEmpty
    private String companyName;

    @NotNull
    private Long quantity;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    private Long unityPrice;

    @NotNull
    private Long walletId;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

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
