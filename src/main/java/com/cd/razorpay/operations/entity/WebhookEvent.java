package com.cd.razorpay.operations.entity;

import com.cd.razorpay.common.enums.WebhookEventStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "webhook_event")
public class WebhookEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Column(nullable = false,length = 100)
    private String eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb",name = "payload")
    private Map<String,Object> payload;

    @Column(nullable = false)
    private String targetUrl;

    @Column(nullable = false)
    private String signature;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "status")
    private WebhookEventStatus status;

    @Column(nullable = false)
    private Integer attempts;


    private LocalDateTime nextRetryAt;

    private LocalDateTime lastAttemptAt;

    private Integer LastResponseCode;

    @Column(length = 1000)
    private String LastResponseBody;

    private LocalDateTime deliveryAt;




}
