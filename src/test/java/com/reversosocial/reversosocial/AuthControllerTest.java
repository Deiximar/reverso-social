package com.reversosocial.reversosocial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.reversosocial.controller.AuthController;
import com.reversosocial.models.dto.AuthResponseDto;
import com.reversosocial.models.dto.LoginDto;
import com.reversosocial.models.dto.RegisterDto;
import com.reversosocial.service.UserService;

public class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLoginAUserSuccessfully() {
        // Given
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@test.com");
        loginDto.setPassword("password");

        AuthResponseDto authResponseDto = new AuthResponseDto("token");

        // When
        when(userService.login(loginDto)).thenReturn(authResponseDto);

        // Then
        ResponseEntity<AuthResponseDto> response = authController.login(loginDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authResponseDto, response.getBody());

    }

    @Test
    void shouldRegisterANewUserSuccessfully() {
        // Given
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("Maria07");
        registerDto.setName("Maria");
        registerDto.setLastname("Gonzalez");
        registerDto.setPassword("12345678");
        registerDto.setEmail("mariagonzalez@test.com");

        LocalDate birthday = LocalDate.of(1965, 9, 30);
        registerDto.setBirthday(birthday);

        String responseMessage = "User registered successfully";

        // When
        when(userService.register(registerDto)).thenReturn(responseMessage);

        // then
        ResponseEntity<String> response = authController.register(registerDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMessage, response.getBody());

        assertEquals("1965-09-30", birthday.toString());
        verify(userService, times(1)).register(registerDto);

    }

}
