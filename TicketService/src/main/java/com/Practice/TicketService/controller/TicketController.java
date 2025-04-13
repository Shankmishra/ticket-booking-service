package com.Practice.TicketService.controller;

import com.Practice.TicketService.dto.TicketRequestBody;
import com.Practice.TicketService.dto.TicketResponseBody;
import com.Practice.TicketService.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Tickets")
@RequiredArgsConstructor
public class TicketController {


    private final TicketService ticketservice;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTickets(@RequestBody TicketRequestBody TicketRequestBody){
        ticketservice.addTicket(TicketRequestBody);

    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TicketResponseBody> showTickets(){
        return ticketservice.showTickets();

    }
}
