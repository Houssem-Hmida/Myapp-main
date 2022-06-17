package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.ILogAccessService;
import com.example.myapp.persistence.model.LogAccess;
import com.example.myapp.persistence.repository.LogAccessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Slf4j

public class LogAccessService implements ILogAccessService {


    @Autowired
    private LogAccessRepository logAccessRepository;


    @Override
    public void saveLogAccess(String codeAccess, String username) {
        try {
            LogAccess logAccess = new LogAccess();
            logAccess.setCodeAccess(codeAccess);
            logAccess.setDateAuth(new Timestamp(new Date().getTime()));
            logAccess.setUsername(username);

            log.info("Saving new logaccess to the databse ");
            logAccessRepository.save(logAccess);
        } catch (Exception e) {
            throw new IllegalStateException("Error LogAccessService in method saveLogAccess :: "+e.toString());
        }
    }

    @Override
    public List<LogAccess> getListLogAccess(){
        try {
            log.info("Fetching all logaccess ");

            return logAccessRepository.findAllByOrderByIdDesc();
        } catch (Exception e) {
            throw  new IllegalStateException("Error LogAcessService in method getListLogAcess " + e.toString());
        }
    }

}
