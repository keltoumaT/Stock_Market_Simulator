package com.masterpiece.stockmarketsimulator.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;


@Entity
public class Deal extends AbstractEntity{

    @Column(length = 255)
    private String companyName;

    private Long quantity;

    private Long unityPrice;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Wallet wallet;

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(Long unityPrice) {
        this.unityPrice = unityPrice;
    }
}
