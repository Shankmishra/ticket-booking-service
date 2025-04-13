package com.Practice.TicketService;

import com.Practice.TicketService.dto.TicketRequestBody;
import com.Practice.TicketService.dto.TicketResponseBody;
import com.Practice.TicketService.repository.TicketRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;


import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class TicketServiceApplicationTests {

	@Container
	static
	MongoDBContainer mt = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private MockMvc mmvc ;

	@Autowired
	private TicketRepository tr;

	@Autowired
	private ObjectMapper obm;

	@DynamicPropertySource
	static void getproperty(DynamicPropertyRegistry dr){
		dr.add("spring.data.mongodb.uri",mt::getReplicaSetUrl);
	}


	@Test
	void TicketAdditionCheck() throws Exception {
		TicketRequestBody Trb = getmockTicket();
		String TrbString = obm.writeValueAsString(Trb);
		mmvc.perform(MockMvcRequestBuilders.post("/api/Tickets")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TrbString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1,tr.findAll().size());
	}

	@Test
	void checkiffetched() throws Exception {
		String response= mmvc.perform(MockMvcRequestBuilders.get("/api/Tickets")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
TicketResponseBody[] tickets = obm.readValue(response,TicketResponseBody[].class);
      Assertions.assertTrue(tickets.length>0);
	  Assertions.assertTrue(tickets[0].getTheaterid().equals("A0ww"));
	}

	private TicketRequestBody getmockTicket(){
		return TicketRequestBody.builder()
				.theaterid("A0ww")
				.price(BigDecimal.valueOf(6770))
				.seatNo("A3")
				.category("Premium")
						.build();

	}
}
