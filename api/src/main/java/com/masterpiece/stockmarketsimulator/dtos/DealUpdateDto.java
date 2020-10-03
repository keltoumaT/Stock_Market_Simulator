package com.masterpiece.stockmarketsimulator.dtos;

import javax.validation.constraints.NotNull;

public class DealUpdateDto {


    @NotNull
    private String companyName;

    @NotNull
    private Long quantity;

    @NotNull
    private Double unityPrice;

    @NotNull
    private String symbol;

    @NotNull
    private Long id;

    @NotNull
    private Long walletId;


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

    public Double getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(Double unityPrice) {
        this.unityPrice = unityPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }
}
