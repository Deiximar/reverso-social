package com.reversosocial.layer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reversosocial.bean.dto.EventDto;
import com.reversosocial.layer.service.EventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class EventController {

  private final EventService eventService;

  @PostMapping
  @PreAuthorize("hasAuthority('CREATE')")
  public ResponseEntity<EventDto> createEvent(@RequestBody @Valid EventDto eventDto) {
    EventDto createdRoutine = eventService.createEvent(eventDto);
    return new ResponseEntity<EventDto>(createdRoutine, HttpStatus.CREATED);
  }

}
