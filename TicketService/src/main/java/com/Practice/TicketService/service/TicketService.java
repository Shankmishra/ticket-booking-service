package com.Practice.TicketService.service;

import com.Practice.TicketService.dto.TicketRequestBody;
import com.Practice.TicketService.dto.TicketResponseBody;
import com.Practice.TicketService.model.Ticket;
import com.Practice.TicketService.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    public void addTicket(TicketRequestBody trb){
        Ticket t = Ticket.builder()
                        .theaterid(trb.getTheaterid())
                                .category(trb.getCategory())
                                        .seatNo(trb.getSeatNo())
                                                .price(trb.getPrice())
                                                        .build();
        ticketRepository.save(t);
    }

    public List<TicketResponseBody> showTickets(){
        List<Ticket> tickets = ticketRepository.findAll();
       List<TicketResponseBody> tr= tickets.stream().map(ticket->mapTicketsForResponse(ticket)).toList();
         return tr;
    }
   public TicketResponseBody mapTicketsForResponse(Ticket ticket){
        TicketResponseBody rbt = TicketResponseBody.builder()
                .id(ticket.getId())
                .theaterid(ticket.getTheaterid())
                .category(ticket.getCategory())
                .seatNo(ticket.getSeatNo())
                .price(ticket.getPrice())
                .build();
        return rbt;

   }

}
