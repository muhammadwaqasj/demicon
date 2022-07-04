package de.demicon.connector.controller;

import de.demicon.connector.record.Countries;
import de.demicon.connector.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsService userDetailsService;

    @GetMapping
    public List<Countries> getUsersDetails() {
        return userDetailsService.getUserDetails();
    }
}
