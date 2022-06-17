package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.ILogDataService;
import com.example.myapp.persistence.model.LogData;
import com.example.myapp.persistence.repository.LogDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LogDataService implements ILogDataService {

    @Autowired
    private LogDataRepository logDataRepository;


    @Override
    public void saveLogData(String username, String action) {
        try {
            LogData logData = new LogData();
            logData.setUsername(username);
            logData.setAction(action);
            log.info("Saving new logdata {} to the databse ",action);
            logDataRepository.save(logData);
        } catch (Exception e) {
            throw new IllegalStateException("Error LogDataService in method saveLogData :: "+e.toString());
        }
    }

    @Override
    public List<LogData> getListLogData(){
        try {
            log.info("Fetching all logdata ");
            return logDataRepository.findAllByOrderByIdDesc();
        } catch (Exception e) {
            throw  new IllegalStateException("Error LogDataService in method getListLogData " + e.toString());
        }
    }
}