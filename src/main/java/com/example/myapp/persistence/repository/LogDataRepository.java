package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.LogData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface LogDataRepository extends JpaRepository<LogData,Long> {
    public List<LogData> findAllByOrderByIdDesc();

}
