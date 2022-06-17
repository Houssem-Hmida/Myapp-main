package com.example.myapp.business.service;

import com.example.myapp.persistence.model.LogAccess;

import java.util.List;

public interface ILogAccessService {
    public void saveLogAccess(String codeAcceess, String username);

    List<LogAccess> getListLogAccess();
}
