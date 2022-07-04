package de.demicon.connector.controller;

import de.demicon.connector.record.Countries;
import de.demicon.connector.service.UserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    private MockMvc mockMvc;

    @InjectMocks
    UserController userController;

    @Mock UserDetailsService userDetailsService;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsers_success() {
        List<Countries> countries = new ArrayList<>();
        Mockito.when(userDetailsService.getUserDetails()).thenReturn(countries);
    }


}
