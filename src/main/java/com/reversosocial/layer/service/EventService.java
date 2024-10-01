package com.reversosocial.layer.service;

import java.util.List;

import com.reversosocial.bean.dto.EventDto;

public interface EventService {
  EventDto createEvent(EventDto event);

  List<EventDto> getAllEvents();

  String deleteEvent(Integer eventId);

  EventDto updateEvent(Integer eventId, EventDto eventDto);

  EventDto getEventById(Integer eventId);
}
