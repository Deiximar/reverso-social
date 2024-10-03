package com.reversosocial.reversosocial.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reversosocial.models.dto.AuthResponseDto;
import com.reversosocial.models.dto.EventDto;
import com.reversosocial.models.dto.LoginDto;
import com.reversosocial.models.entity.EPermission;
import com.reversosocial.models.entity.ERole;
import com.reversosocial.models.entity.Event;
import com.reversosocial.models.entity.Permission;
import com.reversosocial.models.entity.Role;
import com.reversosocial.models.entity.Sector;
import com.reversosocial.models.entity.User;
import com.reversosocial.repository.EventRepository;
import com.reversosocial.repository.PermissionRepository;
import com.reversosocial.repository.RoleRepository;
import com.reversosocial.repository.SectorRepository;
import com.reversosocial.repository.UserRepository;
import com.reversosocial.service.EventService;
import com.reversosocial.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EventControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  EventService eventService;

  @Autowired
  EventRepository eventRepository;

  @Autowired
  SectorRepository sectorRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PermissionRepository permissionRepository;

  @Autowired
  UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private ObjectMapper objectMapper;

  List<Event> events;
  List<EventDto> eventsDto;
  Role role;
  User user;
  Sector sector;
  AuthResponseDto token;

  @BeforeEach
  void setUp() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      connection.createStatement().execute("ALTER SEQUENCE events_id_seq RESTART WITH 1");
    }
    eventRepository.deleteAll();
    userRepository.deleteAll();
    roleRepository.deleteAll();
    permissionRepository.deleteAll();
    sectorRepository.deleteAll();

    Permission readPermission = Permission.builder().permission(EPermission.READ).build();
    Permission createPermission = Permission.builder().permission(EPermission.CREATE).build();
    Permission updatePermission = Permission.builder().permission(EPermission.UPDATE).build();
    Permission deletePermission = Permission.builder().permission(EPermission.DELETE).build();
    permissionRepository.saveAll(List.of(readPermission, createPermission, updatePermission, deletePermission));

    role = Role.builder().role(ERole.FEMSENIOR)
        .permissionList(Set.of(readPermission, createPermission, updatePermission, deletePermission))
        .build();

    user = User.builder()
        .username("testuser")
        .name("testname")
        .lastname("testlastname")
        .password(passwordEncoder.encode("password"))
        .email("testuser@example.com")
        .birthday(LocalDate.of(1970, 01, 20))
        .role(role)
        .build();

    roleRepository.save(role);
    userRepository.save(user);

    LoginDto loginDto = LoginDto.builder().email(user.getEmail()).password("password").build();
    token = userService.login(loginDto);

    sector = Sector.builder().sector("Tecnología").build();

    Event event1 = Event.builder()
        .title("Evento 1")
        .description("Descripcion del evento 1")
        .date(LocalDate.of(2024, 12, 10))
        .time(LocalTime.of(14, 0, 0))
        .modality("Online")
        .location("Zoom")
        .sector(sector)
        .maxParticipants(15)
        .user(user)
        .build();
    Event event2 = Event.builder()
        .title("Evento 2")
        .description("Descripcion del evento 2")
        .date(LocalDate.of(2024, 12, 12))
        .time(LocalTime.of(14, 0, 0))
        .modality("Online")
        .location("Zoom")
        .sector(sector)
        .maxParticipants(15)
        .user(user)
        .build();

    this.events = new ArrayList<>();
    this.events.add(event1);
    this.events.add(event2);

    roleRepository.save(role);
    sectorRepository.save(sector);
    eventRepository.saveAll(List.of(event1, event2));
  }

  @Test
  void shouldFindAllEventsSuccesfully() throws Exception {
    this.mockMvc.perform(get("/api/events").accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Evento 1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Evento 2"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value("2024-12-10"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].date").value("2024-12-12"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].modality").value("Online"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].location").value("Zoom"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].maxParticipants").value(15))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].sector").value("Tecnología"));
  }

  @Test
  void shouldFindAnEventSuccesfully() throws Exception {
    this.mockMvc.perform(get("/api/events/1").accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Evento 1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2024-12-10"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.modality").value("Online"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("Zoom"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.maxParticipants").value(15))
        .andExpect(MockMvcResultMatchers.jsonPath("$.sector").value("Tecnología"));
  }

  @Test
  void shouldSaveAnEventSuccesfully() throws Exception {
    EventDto eventDto = EventDto.builder()
        .title("Evento 3")
        .description("Descripcion del evento 3")
        .date(LocalDate.of(2024, 12, 12))
        .time(LocalTime.of(14, 0, 0))
        .modality("Online")
        .location("Zoom")
        .sector("Tecnología")
        .maxParticipants(15)
        .build();

    String eventDtoJson = objectMapper.writeValueAsString(eventDto);
    this.mockMvc.perform(post("/api/events")
        .contentType(MediaType.APPLICATION_JSON).content(eventDtoJson).accept(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getAccessToken()))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Evento 3"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2024-12-12"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.time").value("14:00:00"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.modality").value("Online"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("Zoom"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.maxParticipants").value(15))
        .andExpect(MockMvcResultMatchers.jsonPath("$.sector").value("Tecnología"));
  }

  @Test
  void shouldUpdateAnEventSuccesfully() throws Exception {
    EventDto eventDto = EventDto.builder()
        .title("Evento 2")
        .description("Descripcion del evento 2")
        .date(LocalDate.of(2024, 12, 10))
        .time(LocalTime.of(10, 0, 0))
        .modality("Presencial")
        .location("Madrid")
        .sector("Tecnología")
        .maxParticipants(15)
        .build();

    String eventDtoJson = objectMapper.writeValueAsString(eventDto);
    this.mockMvc.perform(put("/api/events/2")
        .contentType(MediaType.APPLICATION_JSON).content(eventDtoJson).accept(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getAccessToken()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Evento 2"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2024-12-10"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.time").value("10:00:00"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.modality").value("Presencial"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("Madrid"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.maxParticipants").value(15))
        .andExpect(MockMvcResultMatchers.jsonPath("$.sector").value("Tecnología"));
  }

  @Test
  void shouldDeleteAnEventSuccesfully() throws Exception {
    this.mockMvc.perform(delete("/api/events/2")
        .accept(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getAccessToken()))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
