package com.pedrolima.eventmanager.services;

import java.time.Instant;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrolima.eventmanager.dto.EventDTO;
import com.pedrolima.eventmanager.dto.EventSubscriptionsDTO;
import com.pedrolima.eventmanager.entities.Event;
import com.pedrolima.eventmanager.exceptions.BusinessException;
import com.pedrolima.eventmanager.exceptions.ResourceNotFoundException;
import com.pedrolima.eventmanager.mapper.EventMapper;
import com.pedrolima.eventmanager.mapper.EventSubscriptionMapper;
import com.pedrolima.eventmanager.repositories.EventRepository;

@Service
public class EventService {

	EventRepository eventRepository;
	EventMapper eventMapper;
	EventSubscriptionMapper eventSubscriptionMapper;

	@Autowired
	public EventService(EventRepository eventRepository, EventMapper eventMapper,
			EventSubscriptionMapper eventSubscriptionMapper) {
		super();
		this.eventRepository = eventRepository;
		this.eventMapper = eventMapper;
		this.eventSubscriptionMapper = eventSubscriptionMapper;
	}

	@Transactional(readOnly = true)
	public Page<Event> findAll(Pageable pageable) {
		return eventRepository.findAll(pageable);
	}

	public Event findById(Long id) {
		return eventRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Event not found for id: " + id));
	}

	public Event insert(EventDTO eventDto) {
		Event event = eventMapper.toEntity(eventDto);
		return eventRepository.save(event);
	}

	public EventSubscriptionsDTO findAllEventSubscriptions(Long id) {
		Event event = eventRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Event not found for Id: " + id));
		EventSubscriptionsDTO responseDto = new EventSubscriptionsDTO();
		responseDto.setId(event.getId());
		responseDto.setName(event.getName());
		responseDto.setSubscriptions(eventSubscriptionMapper.toDto(event.getSubscriptions()));
		return responseDto;
	}

	public void decreaseVacancies(Event event) {
		if (event.getVacancies() > 0) {
			event.setVacancies(event.getVacancies() - 1);
		} else {
			throw new BusinessException("Event is full!");
		}
	}

	public void checkBeginDateTime(Event event) {
		if (Instant.now().isAfter(event.getBeginDateTime().toInstant(ZoneOffset.UTC))) {
			throw new BusinessException("Registration Closed!");
		}

	}

}
