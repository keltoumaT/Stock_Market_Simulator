package com.masterpiece.stockmarketsimulator.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class DealDto {

    @NotEmpty
    private String companyName;

    @NotNull
    private Long quantity;

    //@JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
    //private LocalDateTime date;

    @NotNull
    @Positive
    private Double unityPrice;

    @NotNull
    private Long walletId;

    @NotNull
    private String symbol;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Double getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(Double unityPrice) {
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

   // public LocalDateTime getDate() {
    //    return date;
  //  }

   // public void setDate(LocalDateTime date) {
   //     this.date = date;
   // }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
