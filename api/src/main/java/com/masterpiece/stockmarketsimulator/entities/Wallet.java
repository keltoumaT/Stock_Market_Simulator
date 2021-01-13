package com.masterpiece.stockmarketsimulator.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Wallet extends AbstractEntity{

    @Column(length = 100)
    private String name;

    @Column(length = 255)
    private String memo;

    private Double capital;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CustomUser customUser;


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

    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }

}
