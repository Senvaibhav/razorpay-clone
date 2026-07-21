package com.cd.razorpay.payment.entity;

import com.cd.razorpay.common.enums.PaymentActor;
import com.cd.razorpay.common.enums.PaymentEvent;
import com.cd.razorpay.common.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_transition_log")
public class PaymentTransitionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "payment_id",nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20,name = "from_status")
    private PaymentStatus fromStatus;


    @Enumerated(EnumType.STRING)
    @Column(name = "event",nullable = false,length = 30)
    private PaymentEvent event;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20,name = "to_status")
    private PaymentStatus toStatus;

    @Column(length = 100,name = "actor")
    private PaymentActor actor;

    @Column(name = "occurred_at",nullable = false)
    private LocalDateTime occurredAt;

}
