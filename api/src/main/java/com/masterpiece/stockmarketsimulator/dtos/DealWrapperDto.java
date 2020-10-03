package com.masterpiece.stockmarketsimulator.dtos;

import javax.validation.constraints.NotNull;

public class DealWrapperDto {

    @NotNull
    private DealDto dealDto;

    @NotNull
    private DealUpdateDto dealUpdateDto;

    public DealDto getDealDto() {
        return dealDto;
    }

    public void setDealDto(DealDto dealDto) {
        this.dealDto = dealDto;
    }

    public DealUpdateDto getDealUpdateDto() {
        return dealUpdateDto;
    }

    public void setDealUpdateDto(DealUpdateDto dealUpdateDto) {
        this.dealUpdateDto = dealUpdateDto;
    }
}
