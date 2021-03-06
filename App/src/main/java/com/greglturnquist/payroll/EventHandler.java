package com.greglturnquist.payroll;

import static com.greglturnquist.payroll.WebSocketConfiguration.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Customer.class)
public class EventHandler {

	private final SimpMessagingTemplate websocket;

	private final EntityLinks entityLinks;

	@Autowired
	public EventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
		this.websocket = websocket;
		this.entityLinks = entityLinks;
	}

	@HandleAfterCreate
	public void newCustomer(Customer customer) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/newCustomer", getPath(customer));
	}

	@HandleAfterDelete
	public void deleteCustomer(Customer customer) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/deleteCustomer", getPath(customer));
	}

	@HandleAfterSave
	public void updateCustomer(Customer customer) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/updateCustomer", getPath(customer));
	}

	/**
	 * Take an {@link Customer} and get the URI using Spring Data REST's {@link EntityLinks}.
	 *
	 * @param customer
	 */
	private String getPath(Customer customer) {
		return this.entityLinks.linkForSingleResource(customer.getClass(),
				customer.getId()).toUri().getPath();
	}

}
