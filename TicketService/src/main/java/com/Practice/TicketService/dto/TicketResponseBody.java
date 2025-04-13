package com.Practice.TicketService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TicketResponseBody {
    private String id;
    private String theaterid;
    private String category;
    private String seatNo;
    private BigDecimal price;
}
