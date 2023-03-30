package com.forex.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forex.dto.ForexResponse;
import com.forex.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ForexService {

    @Autowired
    private RedisRepository redisRepository;

    // first time user will come and use this api for get latest forex rates
    public List<ForexResponse> getForexRate() throws JsonProcessingException {
        String response = redisRepository.findForexRateByPair("USD vs INR");
        return List.of(new ObjectMapper().readValue(response, ForexResponse.class));
    }
}
