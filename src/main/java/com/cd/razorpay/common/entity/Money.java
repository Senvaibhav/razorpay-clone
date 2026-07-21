package com.cd.razorpay.common.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.lang.management.MonitorInfo;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money {
    private int amountUnit;
    private String currency;

    private Money(int amountUnit, String currency) {
        this.amountUnit = amountUnit;
        this.currency = currency;
    }
    public  static Money of(int amountUnit, String currency) {
        return new Money(amountUnit, currency);
    }
    public static Money inr(int amountUnit) {
        return new Money(amountUnit, "INR");
    }


    public Money add(Money other){
        if(!this.currency.equals(other.currency))
            throw new IllegalArgumentException("Currencies do not match");
        return new Money(this.amountUnit + other.amountUnit, this.currency);
    }

    public Money subtract(Money other){
        if(!this.currency.equals(other.currency))
            throw new IllegalArgumentException("Currencies do not match");
        return new Money(this.amountUnit - other.amountUnit, this.currency);
    }

}
