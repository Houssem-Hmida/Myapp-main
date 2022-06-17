package com.example.myapp.persistence.repository;

public interface eventDao {
	 public Integer getStatics (String event_start,String event_end,Long id_salle,Long dep_id,String event_title);
}
