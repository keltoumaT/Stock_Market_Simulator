package com.masterpiece.stockmarketsimulator.dtos;

import java.time.LocalDate;

public interface DealViewDto {

    String getCompanyName();
    Long getQuantity();
    Long getUnityPrice();
    LocalDate getDate();

}
