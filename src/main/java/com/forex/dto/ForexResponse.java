package com.forex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForexResponse {
    private Long id;
    private String pair;
    private BigDecimal sellingPrice;
    private BigDecimal buyingPrice;
}
