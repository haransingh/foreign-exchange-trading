package com.forex.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forex.dto.ForexResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RedisRepository {

    private StringRedisTemplate redisTemplate;

    public boolean saveForexRate(ForexResponse forexResponse) {
        try {
            redisTemplate.opsForValue().set(forexResponse.getPair(), new ObjectMapper().writeValueAsString(forexResponse));
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    public String findForexRateByPair(String pair) {
       return redisTemplate.opsForValue().get(pair);
    }

}
