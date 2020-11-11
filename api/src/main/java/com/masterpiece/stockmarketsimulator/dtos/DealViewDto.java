package com.masterpiece.stockmarketsimulator.dtos;

import java.time.LocalDateTime;

public interface DealViewDto {

    String getCompanyName();
    Long getQuantity();
    Double getUnityPrice();
    LocalDateTime getDate();
    String getSymbol();
    Long getId();
    Long getWalletId();
    Long getUserId();

}
