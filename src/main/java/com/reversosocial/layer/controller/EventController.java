package com.reversosocial.layer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reversosocial.bean.dto.EventDto;
import com.reversosocial.layer.service.EventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

/*@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

  private final EventService eventService;

  @PostMapping
  public ResponseEntity<EventDto> createEvent(@RequestBody @Valid EventDto eventDto) {
    EventDto createdRoutine = eventService.createEvent(eventDto);
    return new ResponseEntity<EventDto>(createdRoutine, HttpStatus.CREATED);
  }

}*/
