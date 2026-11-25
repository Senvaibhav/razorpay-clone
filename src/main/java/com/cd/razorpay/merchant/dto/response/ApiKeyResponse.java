package com.cd.razorpay.merchant.dto.response;

import com.cd.razorpay.common.enums.Environment;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApiKeyResponse(
        UUID id,
        String keyId,
        Environment environment,
        boolean enables,
        LocalDateTime lastUsedAt,
        LocalDateTime createdAt
) {
}
