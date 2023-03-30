package com.forex.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.forex.dto.ForexResponse;
import com.forex.service.ForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/forex")
public class ForexController {

    @Autowired
    private ForexService forexService;

    @GetMapping("get-forex-rate")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<ForexResponse>> getForexRate() throws JsonProcessingException {
        return new ResponseEntity<>(forexService.getForexRate(), HttpStatus.OK);
    }

}
