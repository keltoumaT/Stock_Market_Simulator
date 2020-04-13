package com.masterpiece.stockmarketsimulator.dtos;

import com.masterpiece.stockmarketsimulator.entities.Member;

public interface WalletViewDto {

    String getName();
    String getMemo();
    Long getInitialCapital();
    Long getId();
}
