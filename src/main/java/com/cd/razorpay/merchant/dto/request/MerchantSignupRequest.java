package com.cd.razorpay.merchant.dto.request;

import com.cd.razorpay.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignupRequest(
        @NotNull(message = "Name should be provided")
        @Size(max = 50,message = "Name should not be more than 50 character")
        String name,

        @Email
        @NotNull(message = "Email is required")
        String email,

        @NotNull(message = "Password is required")
        @Size(min = 8,message = "Password should be more than 8 character")
        String password,

        @Size(max = 50,message = "Business name should not be more than 50 character long")
        String businessName,

        @Size(max = 12)
        String contactNumber,

        BusinessType businessType

) {
}
