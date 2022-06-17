package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IUtilisateurService;
import com.example.myapp.business.service.JWT.JwtService;
import com.example.myapp.business.service.impl.Initialize;
import com.example.myapp.persistence.JWT.JwtRequest;
import com.example.myapp.persistence.JWT.JwtResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
@Slf4j
@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private Initialize initialize;


   /* @Autowired
    private SimpMessagingTemplate template;
*/
    @Autowired
    private IUtilisateurService iUtilisateurService;

    @PostConstruct
    public void initRoleAndUser() {
        initialize.initRoleAndUser();
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }


}
