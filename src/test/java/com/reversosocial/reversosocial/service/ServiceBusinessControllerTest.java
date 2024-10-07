package com.reversosocial.reversosocial.service;

import java.util.List;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Set;
import java.sql.SQLException;
import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.reversosocial.models.dto.AuthResponseDto;
import com.reversosocial.models.dto.LoginDto;
import com.reversosocial.models.dto.ServiceBusinessDto;
import com.reversosocial.models.entity.EPermission;
import com.reversosocial.models.entity.ERole;
import com.reversosocial.models.entity.Permission;
import com.reversosocial.models.entity.Role;
import com.reversosocial.models.entity.Sector;
import com.reversosocial.models.entity.ServiceBusiness;
import com.reversosocial.models.entity.User;
import com.reversosocial.repository.EventRepository;
import com.reversosocial.repository.PermissionRepository;
import com.reversosocial.repository.RoleRepository;
import com.reversosocial.repository.SectorRepository;
import com.reversosocial.repository.ServiceBusinessRepository;
import com.reversosocial.repository.UserRepository;
import com.reversosocial.service.ServiceBusinessService;
import com.reversosocial.service.UserService;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ServiceBusinessControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ServiceBusinessService serviceeBusinessService;

  @Autowired
  ServiceBusinessRepository serviceBusinessRepository;

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
  EventRepository eventRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private ObjectMapper objectMapper;

  List<ServiceBusiness> serviceBusiness;
  List<ServiceBusinessDto> serviceBusinessDto;
  Role role;
  User user;
  Sector sector;
  AuthResponseDto token;
  
  @AfterEach
  void setUpAfter(){
    eventRepository.deleteAll();
    serviceBusinessRepository.deleteAll();
    userRepository.deleteAll();
    roleRepository.deleteAll();
    permissionRepository.deleteAll();
    sectorRepository.deleteAll();
  }
  @BeforeEach
  void setUp() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
        connection.createStatement().execute("ALTER SEQUENCE services_id_seq RESTART WITH 1");
    }
    eventRepository.deleteAll();
    serviceBusinessRepository.deleteAll();
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
        .username("testUser2")
        .name("testname2")
        .lastname("testlastname2")
        .password(passwordEncoder.encode("password"))
        .email("testuser2@example.com")
        .birthday(LocalDate.of(1970, 01, 20))
        .role(role)
        .build();

        roleRepository.save(role);
        userRepository.save(user);

        LoginDto loginDto = LoginDto.builder().email(user.getEmail()).password("password").build();
        token = userService.login(loginDto);

        sector = Sector.builder().sector("Terapias Alternativas y Desarrollo Personal").build();
        sectorRepository.save(sector);

        ServiceBusiness service1 = ServiceBusiness.builder()
            .title("Servicio 1")
            .type("Tipo 1")
            .description("Descripción del servicio 1")
            .email("service1@example.com")
            .phone_number("+34623344565")
            .sector(sector)
            .user(user)
            .build();

        ServiceBusiness service2 = ServiceBusiness.builder()
            .title("Servicio 2")
            .type("Tipo 2")
            .description("Descripción del servicio 2")
            .email("service2@example.com")
            .phone_number("+34623344564")
            .sector(sector)
            .user(user)
            .build();

        serviceBusiness = List.of(service1, service2);
        serviceBusinessRepository.saveAll(serviceBusiness);
    }

    @Test
    void shouldFindAllServicesSuccessfully() throws Exception {
        mockMvc.perform(get("/api/services").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(serviceBusiness.size()))
            .andExpect(jsonPath("$[0].title").value("Servicio 1"))
            .andExpect(jsonPath("$[1].title").value("Servicio 2"));
    }

    @Test
    void shouldFindServiceByIdSuccessfully() throws Exception {
        mockMvc.perform(get("/api/services/1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Servicio 1"))
            .andExpect(jsonPath("$.description").value("Descripción del servicio 1"));
    }

    @Test
    void shouldSaveServiceSuccessfully() throws Exception {
        ServiceBusinessDto serviceBusinessDto = ServiceBusinessDto.builder()
            .title("Nuevo Servicio")
            .type("Nuevo Tipo")
            .description("Descripción del nuevo servicio")
            .email("nuevo@example.com")
            .phone_number("+34623344565")
            .sector("Terapias Alternativas y Desarrollo Personal")
            .build();

        String serviceBusinessDtoJson = objectMapper.writeValueAsString(serviceBusinessDto);
        mockMvc.perform(post("/api/services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(serviceBusinessDtoJson)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getAccessToken()))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value("Nuevo Servicio"))
            .andExpect(jsonPath("$.description").value("Descripción del nuevo servicio"));
    }

    @Test
    void shouldUpdateServiceSuccessfully() throws Exception {
        ServiceBusinessDto serviceBusinessDto = ServiceBusinessDto.builder()
            .title("Servicio Actualizado")
            .type("Tipo Actualizado")
            .description("Descripción actualizada")
            .email("update@example.com")
            .phone_number("+34623344565")
            .sector("Terapias Alternativas y Desarrollo Personal")
            .build();

        String serviceBusinessDtoJson = objectMapper.writeValueAsString(serviceBusinessDto);
        mockMvc.perform(put("/api/services/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(serviceBusinessDtoJson).accept(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getAccessToken()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Servicio Actualizado"))
            .andExpect(jsonPath("$.description").value("Descripción actualizada"));
    }
 
    @Test
    void shouldDeleteServiceSuccessfully() throws Exception {
        mockMvc.perform(delete("/api/services/1")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getAccessToken()))
            .andExpect(status().isOk());
    }
}