package com.masterpiece.stockmarketsimulator.dtos;

import com.masterpiece.stockmarketsimulator.entities.Member;
import com.masterpiece.stockmarketsimulator.entities.Deal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class WalletDto {

    @NotEmpty
    @Size(min = 2, max = 50)
    private String name;

    @Size(max = 255)
    private String memo;

    @NotEmpty
    private Long initialCapital;

    @NotNull
    private Long memberId;

    private Set<Deal> deals;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getInitialCapital() {
        return initialCapital;
    }

    public void setInitialCapital(Long initialCapital) {
        this.initialCapital = initialCapital;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Set<Deal> getDeals() {
        return deals;
    }

    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
    }
}
