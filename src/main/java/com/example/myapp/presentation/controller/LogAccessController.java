package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.ILogAccessService;
import com.example.myapp.persistence.model.LogAccess;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/logaccess")
@AllArgsConstructor
public class LogAccessController {
    private final ILogAccessService iLogAccessService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogAccess> getListLogAccess() {
        try {
            return iLogAccessService.getListLogAccess();
        } catch (Exception e) {
            throw new IllegalStateException("Error LogAccessController in method getListLogAccess :: " + e.toString());
        }
    }
}
