package com.example.myapp.business.service;

import com.example.myapp.persistence.model.LogAccess;
import com.example.myapp.persistence.model.LogData;

import java.util.List;

public interface ILogDataService {

    public void saveLogData(String username, String action);

    List<LogData> getListLogData();
}
