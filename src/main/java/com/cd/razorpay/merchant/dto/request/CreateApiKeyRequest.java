package com.cd.razorpay.merchant.dto.request;

import com.cd.razorpay.common.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment
) {
}
