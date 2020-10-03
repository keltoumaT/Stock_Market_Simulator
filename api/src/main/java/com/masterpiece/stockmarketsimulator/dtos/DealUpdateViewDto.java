package com.masterpiece.stockmarketsimulator.dtos;


public interface DealUpdateViewDto {

    String getCompanyName();
    Long getQuantity();
    Double getUnityPrice();
    String getSymbol();
    Long getId();
    Long getWalletId();
}
