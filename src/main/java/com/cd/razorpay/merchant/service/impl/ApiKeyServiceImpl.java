package com.cd.razorpay.merchant.service.impl;


import com.cd.razorpay.common.exception.ResourceNotFoundException;
import com.cd.razorpay.common.util.RandomizerUtil;
import com.cd.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.cd.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.cd.razorpay.merchant.dto.response.ApiKeyResponse;
import com.cd.razorpay.merchant.entity.ApiKey;
import com.cd.razorpay.merchant.entity.Merchant;
import com.cd.razorpay.merchant.repository.ApiKeyRepository;
import com.cd.razorpay.merchant.repository.MerchantRepository;
import com.cd.razorpay.merchant.service.ApiKeyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;

    @Override
    @Transactional
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant
                merchant=merchantRepository.findById(merchantId)
                .orElseThrow(()-> new ResourceNotFoundException("merchant",merchantId));

        String keyId= "rzp_"+request.environment().name().toLowerCase()+"_"+ RandomizerUtil.randomBase64(24);   //TODO:We have to do this string -- COMPLETED
        String rawSecret=RandomizerUtil.randomBase64(40);     //TODO: replace with cryptographic random hex --COMPLETED


        ApiKey apiKey=ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawSecret) //TODO:encode with BcryptPasswordEncoder -- PENDING
                .environment(request.environment())
                .build();

        apiKey=apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(),keyId,rawSecret,request.environment());

    }

    @Override
    @Transactional
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        return apiKeyRepository.findByMerchant_Id(merchantId).stream()
                .map(apiKey -> new ApiKeyResponse(
                        apiKey.getId(),
                        apiKey.getKeyId(),
                        apiKey.getEnvironment(),
                        apiKey.isEnabled(),
                        apiKey.getLastUsedAt(),
                        null
                )).toList();
    }



    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {
        ApiKey key=apiKeyRepository.findById(keyId)
                .filter(k->k.getMerchant().getId().equals(merchantId))
                .orElseThrow(()->new ResourceNotFoundException("ApiKey",keyId));

        key.setEnabled(false);
    }

    @Override
    @Transactional
    public ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId) {
        ApiKey key=apiKeyRepository.findById(keyId)
                .filter(k->k.getMerchant().getId().equals(merchantId))
                .orElseThrow(()->new ResourceNotFoundException("ApiKey",keyId));

        String newRawSecret=RandomizerUtil.randomBase64(40);
        key.setPreviousKeySecretHash(key.getKeySecretHash());
        key.setKeySecretHash(newRawSecret);
        key.setRotatedAt(LocalDateTime.now());
        key.setGracePeriodExpireAt(LocalDateTime.now().plusHours(24));
        key=apiKeyRepository.save(key);

        return new ApiKeyCreateResponse(key.getId(),key.getKeyId(),newRawSecret,key.getEnvironment());

    }
}
