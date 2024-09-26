package com.reversosocial.reversosocial.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.reversosocial.bean.dto.AuthResponseDto;
import com.reversosocial.bean.dto.LoginDto;
import com.reversosocial.bean.dto.RegisterDto;
import com.reversosocial.bean.entity.ERole;
import com.reversosocial.bean.entity.Role;
import com.reversosocial.bean.entity.User;
import com.reversosocial.config.exception.ExistingEmailException;
import com.reversosocial.config.exception.InvalidCredentialsException;
import com.reversosocial.config.exception.UsernameNotFoundException;
import com.reversosocial.layer.repository.RoleRepository;
import com.reversosocial.layer.repository.UserRepository;
import com.reversosocial.layer.service.impl.UserServiceImpl;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.reversosocial.config.security.jwt.JWTAuthenticationConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

public class UserServiceImplTest {
  @Mock
  UserRepository userRepository;

  @Mock
  PasswordEncoder passwordEncoder;

  @Mock
  RoleRepository roleRepository;

  @Mock
  JWTAuthenticationConfig jwtAuthenticationConfig;

  @Mock
  AuthenticationManager authenticationManager;

  @InjectMocks
  UserServiceImpl userService;

  List<User> users;

  @BeforeEach
  void setUp() {
    User user1 = new User();
    user1.setId(1);
    user1.setName("user1");
    user1.setLastname("user1");
    user1.setEmail("user1@gmail.com");
    user1.setPassword("password123");
    user1.setUsername("user1");
    user1.setBirthday(LocalDate.of(1965, 5, 5));

    User user2 = new User();
    user2.setId(2);
    user2.setName("user2");
    user2.setLastname("user2");
    user2.setEmail("user2@gmail.com");
    user2.setPassword("password123");
    user2.setUsername("user2");
    user2.setBirthday(LocalDate.of(1995, 5, 5));

    this.users = new ArrayList<>();
    users.add(user1);
    users.add(user2);
  }

  @Test
  void shouldRegisterANewUserSuccessfully() {
    // given
    User newUser = new User();
    newUser.setId(3);
    newUser.setName("user");
    newUser.setLastname("user");
    newUser.setEmail("user@gmail.com");
    newUser.setPassword("password123");
    newUser.setUsername("user");
    newUser.setBirthday(LocalDate.of(1995, 5, 5));

    RegisterDto userDto = new RegisterDto();
    userDto.setName(newUser.getName());
    userDto.setLastname(newUser.getLastname());
    userDto.setEmail(newUser.getEmail());
    userDto.setPassword(newUser.getPassword());
    userDto.setUsername(newUser.getUsername());
    userDto.setBirthday(newUser.getBirthday());

    given(passwordEncoder.encode(userDto.getPassword())).willReturn("encodedPassword");

    Role userRole = new Role();
    userRole.setRole(ERole.ROLE_USER);
    given(roleRepository.findByRole(ERole.ROLE_USER)).willReturn(Optional.of(userRole));

    String responseMessage = "Usuario registrado exitosamente";

    // when
    String response = userService.register(userDto);
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
    verify(userRepository, times(1)).save(userCaptor.capture());
    User savedUser = userCaptor.getValue();

    // then
    assertEquals(responseMessage, response);
    assertEquals("encodedPassword", savedUser.getPassword());
    assertEquals(userRole, savedUser.getRoles().iterator().next());
    assertEquals(newUser.getEmail(), savedUser.getEmail());
  }

  @Test
  void shouldThrowExceptionWhenEmailAlreadyExists() {
    // given
    User newUser = new User();
    newUser.setId(3);
    newUser.setName("user");
    newUser.setLastname("user");
    newUser.setEmail("user@gmail.com");
    newUser.setPassword("password123");
    newUser.setUsername("user");
    newUser.setBirthday(LocalDate.of(1995, 5, 5));

    RegisterDto userDto = new RegisterDto();
    userDto.setName(newUser.getName());
    userDto.setLastname(newUser.getLastname());
    userDto.setEmail(newUser.getEmail());
    userDto.setPassword(newUser.getPassword());
    userDto.setUsername(newUser.getUsername());
    userDto.setBirthday(newUser.getBirthday());

    User existingUser = new User();
    existingUser.setEmail("user@gmail.com");

    given(userRepository.findByEmail(userDto.getEmail())).willReturn(Optional.of(existingUser));
    String responseMessage = "Este correo electronico ya esta en uso.";

    // when & then
    ExistingEmailException response = assertThrows(ExistingEmailException.class, () -> {
      userService.register(userDto);
    });

    verify(userRepository, times(0)).save(any(User.class));
    assertEquals(responseMessage, response.getMessage());

  }

  @Test 
  void shouldLoginUserSuccessfully() {
    // given
    LoginDto request = new LoginDto();
    request.setEmail("test@test.com");
    request.setPassword("password123");
    User user = new User();
    user.setEmail("test@test.com");
    String token = "mockedJwtToken";
    given(userRepository.findByEmail(request.getEmail())).willReturn(Optional.of(user));
    given(jwtAuthenticationConfig.getJWToken(user.getEmail())).willReturn(token);
    Authentication auth = mock(Authentication.class);
    given(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).willReturn(auth);
    // when
    AuthResponseDto response = userService.login(request);
    // then
    assertEquals(token, response.getAccessToken());

  }

  @Test
    void shouldThrowInvalidCredentialsExceptionWhenBadCredentials() {
        // given
        LoginDto LoginDto = new LoginDto();
        LoginDto.setEmail("test@test.com");
        LoginDto.setPassword("wrongPassword");

        User user = new User();
        user.setEmail("test@test.com");

        given(userRepository.findByEmail(LoginDto.getEmail())).willReturn(Optional.of(user));

        doThrow(new BadCredentialsException("Invalid password"))
            .when(authenticationManager)
            .authenticate(any(UsernamePasswordAuthenticationToken.class));

        // when & then
        assertThrows(InvalidCredentialsException.class, () -> {
            userService.login(LoginDto);
        });
    }

    @Test
    void shouldThrowUsernameNotFoundExceptionWhenUserDoesNotExist() {
        // given
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("nonexistent@test.com");
        loginDto.setPassword("password123");

        given(userRepository.findByEmail(loginDto.getEmail())).willReturn(Optional.empty());

        // when & then
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.login(loginDto);
        });

        verify(authenticationManager, never()).authenticate(any());
    }

}