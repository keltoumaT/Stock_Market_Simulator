package com.masterpiece.stockmarketsimulator.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Wallet extends AbstractEntity{

    @Column(length = 100)
    private String name;

    @Column(length = 255)
    private String memo;

    private Long initialCapital;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;

    @OneToMany
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
