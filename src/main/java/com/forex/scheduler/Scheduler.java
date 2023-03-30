package com.forex.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forex.dto.ForexResponse;
import com.forex.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Scheduler {
   @Autowired
   private RestTemplate restTemplate;

   @Autowired
   private RedisRepository redisRepository;


   @Scheduled(fixedRate = 30000)
   public void fixedDelaySch() throws JsonProcessingException {
      ForexResponse forexResponse = restTemplate.getForObject("https://a08b527f-f3d4-4396-9b69-924c79b66e79.mock.pstmn.io/5exceptions/bank/forex?pair=USD vs INR", ForexResponse.class);
      // saving updated price into the redis server
      boolean isPersist = redisRepository.saveForexRate(forexResponse);
      if (isPersist) {
         System.out.println("forex rate has stored on redis successfully.");
      } else {
         System.out.println("failed to stored forex rate on redis.");
      }

      // TODO: save forex rate as well into the database


   }
}