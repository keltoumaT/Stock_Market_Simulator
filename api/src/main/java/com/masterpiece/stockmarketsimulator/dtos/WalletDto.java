package com.masterpiece.stockmarketsimulator.dtos;

import com.masterpiece.stockmarketsimulator.entities.Member;
import com.masterpiece.stockmarketsimulator.entities.Share;

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
    private Member member;

    private Set<Share> shares;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Set<Share> getShares() {
        return shares;
    }

    public void setShares(Set<Share> shares) {
        this.shares = shares;
    }
}
