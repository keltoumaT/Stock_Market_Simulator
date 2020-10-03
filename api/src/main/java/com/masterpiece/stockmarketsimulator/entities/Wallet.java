package com.masterpiece.stockmarketsimulator.entities;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Proxy(lazy = false)
public class Wallet extends AbstractEntity{

    @Column(length = 100)
    private String name;

    @Column(length = 255)
    private String memo;

    private Double capital;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;


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

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double initialCapital) {
        this.capital = initialCapital;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
