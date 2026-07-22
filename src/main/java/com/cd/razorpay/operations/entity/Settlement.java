package com.cd.razorpay.operations.entity;

import com.cd.razorpay.common.entity.Money;
import com.cd.razorpay.common.enums.SettlementStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "settlement")
public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnit", column = @Column(name = "gross_amount",nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "gross_currency",nullable = false))
    })
    private Money grossAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnit", column = @Column(name = "refund_amount",nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "refund_currency",nullable = false))
    })
    private Money refundAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnit", column = @Column(name = "net_amount",nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "net_currency",nullable = false))
    })
    private Money netAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnit", column = @Column(name = "fee_amount",nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "fee_currency",nullable = false))
    })
    private Money feeAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private SettlementStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnit", column = @Column(name = "gst_amount",nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "gst_currency",nullable = false))
    })
    private Money gstAmount;

    @Column(length = 100)
    private String bankReference;

    private LocalDateTime processedAt;

}
