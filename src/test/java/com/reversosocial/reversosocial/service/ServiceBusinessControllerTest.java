package com.reversosocial.reversosocial.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reversosocial.models.dto.ServiceBusinessDto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ServiceBusinessControllerTest {

    @Autowired
    private MockMvc

    @Autowired
    private ObjectMapper ObjectMapper;
    
    @Test
    void shouldCreateService() throws Exception {
        ServiceBusinessDto serviceBusinessDto = new ServiceBusinessDto();
    }
}
