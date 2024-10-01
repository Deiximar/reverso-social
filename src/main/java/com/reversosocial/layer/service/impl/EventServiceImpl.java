package com.reversosocial.layer.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.reversosocial.bean.dto.EventDto;
import com.reversosocial.bean.entity.Event;
import com.reversosocial.bean.entity.Sector;
import com.reversosocial.bean.entity.User;
import com.reversosocial.config.exception.ResourceNotFountException;
import com.reversosocial.config.exception.UsernameNotFoundException;
import com.reversosocial.layer.repository.EventRepository;
import com.reversosocial.layer.repository.SectorRepository;
import com.reversosocial.layer.repository.UserRepository;
import com.reversosocial.layer.service.EventService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final SectorRepository sectorRepository;

  @Override
  public EventDto createEvent(EventDto eventDto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userEmail = authentication.getName();

    User user = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));
    Sector sector = sectorRepository.findBySector(eventDto.getSector())
        .orElseThrow(() -> new ResourceNotFountException("Sector no encontrado"));

    Event event = mapEventToEntity(eventDto);
    event.setUser(user);
    event.setSector(sector);
    Event createdEvent = eventRepository.save(event);
    return mapEventToDto(createdEvent);
  }

  private Event mapEventToEntity(EventDto eventDto) {
    Event event = modelMapper.map(eventDto, Event.class);
    return event;
  }

  private EventDto mapEventToDto(Event event) {
    EventDto eventDto = modelMapper.map(event, EventDto.class);
    return eventDto;
  }
}
