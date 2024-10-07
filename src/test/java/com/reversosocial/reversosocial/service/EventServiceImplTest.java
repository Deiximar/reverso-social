package com.reversosocial.reversosocial.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.reversosocial.config.exception.ResourceNotFoundException;
import com.reversosocial.models.dto.EventDto;
import com.reversosocial.models.entity.Event;
import com.reversosocial.models.entity.Sector;
import com.reversosocial.models.entity.User;
import com.reversosocial.repository.EventRepository;
import com.reversosocial.repository.SectorRepository;
import com.reversosocial.repository.UserRepository;
import com.reversosocial.service.impl.EventServiceImpl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)

public class EventServiceImplTest {

  @Mock
  EventRepository eventRepository;

  @Mock
  UserRepository userRepository;

  @Mock
  SectorRepository sectorRepository;

  @Mock
  Authentication authentication;

  @Mock
  SecurityContext securityContext;

  @Mock
  ModelMapper modelMapper;

  @InjectMocks
  EventServiceImpl eventService;

  List<Event> events;
  User user;
  Sector sector;

  @BeforeEach
  void setUp() {
    sector = Sector.builder().sector("Tecnología").build();
    user = User.builder().email("testuser@example.com").build();
    Event event1 = Event.builder()
        .id(1)
        .title("Evento 1")
        .date(LocalDate.of(2024, 12, 10))
        .time(LocalTime.of(14, 0, 0))
        .modality("Online")
        .location("Zoom")
        .maxParticipants(15)
        .sector(sector)
        .user(user)
        .build();

    Event event2 = Event.builder()
        .id(2)
        .title("Evento 2")
        .date(LocalDate.of(2024, 12, 12))
        .time(LocalTime.of(14, 0, 0))
        .modality("Online")
        .location("Zoom")
        .maxParticipants(15)
        .sector(sector)
        .user(user)
        .build();

    this.events = new ArrayList<>();
    this.events.add(event1);
    this.events.add(event2);
  }

  @Test
  public void shouldFindAllEventsSuccesfully() {
    // given
    EventDto eventDto1 = EventDto.builder()
        .title("Evento 1")
        .date(LocalDate.of(2024, 12, 10))
        .time(LocalTime.of(14, 0, 0))
        .modality("Online")
        .location("Zoom")
        .maxParticipants(15)
        .creatorEmail("testuser@example.com")
        .build();

    EventDto eventDto2 = EventDto.builder()
        .title("Evento 2")
        .date(LocalDate.of(2024, 12, 12))
        .time(LocalTime.of(14, 0, 0))
        .modality("Online")
        .location("Zoom")
        .maxParticipants(15)
        .creatorEmail("testuser@example.com")
        .build();

    given(eventRepository.findAll()).willReturn(this.events);
    given(modelMapper.map(this.events.get(0), EventDto.class)).willReturn(eventDto1);
    given(modelMapper.map(this.events.get(1), EventDto.class)).willReturn(eventDto2);

    // when
    List<EventDto> eventsDto = eventService.getAllEvents();
    verify(eventRepository, times(1)).findAll();

    // then
    assertThat(eventsDto.size()).isEqualTo(events.size());
    assertThat(eventsDto.get(0).getTitle()).isEqualTo(this.events.get(0).getTitle());
    assertThat(eventsDto.get(0).getModality()).isEqualTo(this.events.get(0).getModality());
    assertThat(eventsDto.get(1).getTitle()).isEqualTo(this.events.get(1).getTitle());
    assertThat(eventsDto.get(1).getTime()).isEqualTo(this.events.get(1).getTime());

  }

  @Test
  public void shouldThrowExceptionWhenEventsIsEmpty() {
    // given
    List<Event> events = new ArrayList<>();
    given(eventRepository.findAll()).willReturn(events);
    String message = "No hay eventos disponibles";

    // when and then
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      eventService.getAllEvents();
    });
    verify(eventRepository, times(1)).findAll();
    assertThat(exception.getMessage().equals(message));
  }

  @Test
  public void shouldFindAnEventsByIdSuccesfully() {

    // given
    Integer eventId = 2;
    EventDto eventDto = EventDto.builder()
        .title("Evento 2")
        .date(LocalDate.of(2024, 12, 12))
        .time(LocalTime.of(14, 0, 0))
        .modality("Online")
        .location("Zoom")
        .maxParticipants(15)
        .creatorEmail("testuser@example.com")
        .build();

    given(eventRepository.findById(eventId)).willReturn(Optional.of(this.events.get(1)));
    given(modelMapper.map(this.events.get(1), EventDto.class)).willReturn(eventDto);

    EventDto result = eventService.getEventById(eventId);

    verify(eventRepository, times(1)).findById(eventId);
    assertThat(result).isNotNull();
    assertThat(result.getTitle()).isEqualTo(eventDto.getTitle());
    assertThat(result.getModality()).isEqualTo(eventDto.getModality());
    assertThat(result.getCreatorEmail()).isEqualTo(this.user.getEmail());
  }

  @Test
  public void shouldThrowExceptionWhenEventNotFound() {
    // given
    int eventId = 1;
    given(eventRepository.findById(eventId)).willReturn(Optional.empty());
    String message = "Evento no encontrado";

    // when / then
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      eventService.getEventById(eventId);
    });
    verify(eventRepository).findById(eventId);
    assertThat(exception.getMessage().equals(message));
  }

  @Test
  public void shouldDeleteAnEventWhenUserIsOwner() {
    // given
    given(eventRepository.findById(1)).willReturn(Optional.of(this.events.get(0)));

    given(authentication.getName()).willReturn("testuser@example.com");
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // when
    String result = eventService.deleteEvent(1);

    // then
    verify(eventRepository, times(1)).delete(this.events.get(0));
    assertThat(result.equals("El evento ha sido eliminado exitosamente."));
  }

  @Test
  public void shouldNotDeleteAnEventWhenUserIsNotOwner() {
    // given
    given(eventRepository.findById(1)).willReturn(Optional.of(this.events.get(0)));
    given(authentication.getName()).willReturn("otheruser@example.com");
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // when
    Exception exception = assertThrows(AccessDeniedException.class, () -> {
      eventService.deleteEvent(1);
    });

    // that
    assertThat(exception.getMessage().equals("No tienes permiso para acceder a este recurso."));
    verify(eventRepository, times(0)).deleteById(1);
  }

  @Test
  void shouldCreateANewEventSuccesfully() {
    // given
    SecurityContextHolder.getContext().setAuthentication(authentication);
    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("CREATE");
    Collection<GrantedAuthority> grantedAuthorities = Collections.singletonList(grantedAuthority);
    given(authentication.getAuthorities()).willReturn((Collection) grantedAuthorities);
    given(authentication.getName()).willReturn(user.getEmail());
    given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));
    given(sectorRepository.findBySector("Tecnología")).willReturn(Optional.of(sector));
    given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));
    EventDto eventDto = EventDto.builder()
        .title("Evento 1")
        .date(LocalDate.of(2024, 12, 10))
        .time(LocalTime.of(14, 0))
        .modality("Online")
        .location("Zoom")
        .maxParticipants(15)
        .sector("Tecnología")
        .build();
    given(modelMapper.map(eventDto, Event.class)).willReturn(this.events.get(0));
    given(modelMapper.map(this.events.get(0), EventDto.class)).willReturn(eventDto);
    given(eventRepository.save(any(Event.class))).willReturn(this.events.get(0));

    // when
    EventDto result = eventService.createEvent(eventDto);

    assertThat("Evento 1".equals(result.getTitle()));
    assertThat("Tecnología".equals(result.getSector()));
    assertThat(result.getMaxParticipants().equals(15));

    verify(userRepository, times(1)).findByEmail("testuser@example.com");
    verify(sectorRepository, times(1)).findBySector("Tecnología");
    verify(eventRepository, times(1)).save(any(Event.class));
  }

}