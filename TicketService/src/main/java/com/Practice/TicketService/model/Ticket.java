package com.Practice.TicketService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

//defining as MongoDb document
@Document(value = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Ticket {
    //Spring data annotation
    @Id
    private String id;
    private String theaterid;
    private String category;
    private String seatNo;
    private BigDecimal price;
}
