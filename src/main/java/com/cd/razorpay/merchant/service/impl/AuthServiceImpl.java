package com.cd.razorpay.merchant.service.impl;

import com.cd.razorpay.common.enums.MerchantStatus;
import com.cd.razorpay.common.enums.UserRole;
import com.cd.razorpay.common.exception.DuplicateResourceException;
import com.cd.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.cd.razorpay.merchant.dto.response.MerchantResponse;
import com.cd.razorpay.merchant.entity.AppUser;
import com.cd.razorpay.merchant.entity.Merchant;
import com.cd.razorpay.merchant.repository.AppUserRepository;
import com.cd.razorpay.merchant.repository.MerchantRepository;
import com.cd.razorpay.merchant.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {


    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;


    @Override
    @Transactional
    public MerchantResponse signup(MerchantSignupRequest request) {
        if(merchantRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("DUPLICATE_MERCHANT_EMAIL"
                    ,"Merchant with email already exist: "+request.email());
        }
        Merchant merchant= Merchant.builder()
                .businessName(request.businessName())
                .businessType(request.businessType())
                .name(request.name())
                .email(request.email())
                .contactNumber(request.contactNumber())
                .status(MerchantStatus.PENDING_KYC)
                .build();
        merchant=merchantRepository.save(merchant);
        
        AppUser appUser=AppUser.builder()
                .merchant(merchant)
                .email(request.email())
                .passwordHash(request.password()) //TODO: encrypt using Bcrypt
                .role(UserRole.OWNER)
                .build();

        appUserRepository.save(appUser);




        return new MerchantResponse(merchant.getId(), merchant.getName(),
                merchant.getEmail(), merchant.getBusinessName()
                ,merchant.getBusinessType(),merchant.getStatus());

    }
}
