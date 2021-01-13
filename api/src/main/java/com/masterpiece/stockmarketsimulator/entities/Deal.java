package com.masterpiece.stockmarketsimulator.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Entity
public class Deal extends AbstractEntity {

    @Column(length = 255)
    private String companyName;

    private Long quantity;

    private Double unityPrice;

    private LocalDateTime date;

    @Column(length = 10)
    private String symbol;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Wallet wallet;

    private Long userId;

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    public Double getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(Double unityPrice) {
        this.unityPrice = unityPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
