package com.cd.razorpay.merchant.service;


import com.cd.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.cd.razorpay.merchant.dto.response.MerchantResponse;


public interface AuthService {

    MerchantResponse signup( MerchantSignupRequest request);
}
