package com.reversosocial.layer.service;

import java.util.List;

import com.reversosocial.bean.dto.EventDto;

public interface EventService {
  EventDto createEvent(EventDto event);

  List<EventDto> getAllEvents();
}
