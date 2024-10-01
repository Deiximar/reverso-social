package com.reversosocial.layer.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

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
    return new ResponseEntity<>(createdRoutine, HttpStatus.CREATED);
  }

  @GetMapping
  @PreAuthorize("permitAll()")
  public ResponseEntity<List<EventDto>> getAllEvents() {
    return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('DELETE')")
  public ResponseEntity<String> deleteEvent(@PathVariable Integer id) {
    return new ResponseEntity<>(eventService.deleteEvent(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('UPDATE')")
  public ResponseEntity<EventDto> updateEvent(@PathVariable Integer id, @RequestBody EventDto eventDto) {
    return new ResponseEntity<>(eventService.updateEvent(id, eventDto), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @PreAuthorize("permitAll()")
  public ResponseEntity<EventDto> getEventById(@PathVariable Integer id) {
    return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
  }
}
