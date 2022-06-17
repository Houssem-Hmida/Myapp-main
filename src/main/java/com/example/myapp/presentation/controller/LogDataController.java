package com.example.myapp.presentation.controller;


import com.example.myapp.business.service.ILogDataService;
import com.example.myapp.persistence.model.LogData;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/logdata")
@AllArgsConstructor
public class LogDataController {
    private final ILogDataService iLogDataService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogData> getListLogData() {
        try {
            return iLogDataService.getListLogData();
        } catch (Exception e) {
            throw new IllegalStateException("Error LogDataController in method getListLogData :: " + e.toString());
        }
    }
}
