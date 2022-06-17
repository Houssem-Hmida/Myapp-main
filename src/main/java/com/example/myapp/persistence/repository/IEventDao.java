package com.example.myapp.persistence.repository;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.business.service.impl.EventService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class IEventDao implements eventDao{
	
private EntityManager entityManager;
	
	@Autowired
	public IEventDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	 
	@Override
	public Integer getStatics(String event_start, String event_end, Long id_salle, Long dep_id, String event_title) {
		try {
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("dd-MM-yy");
			String clause="";
			if(event_start!=null)
				clause=clause+ " and TO_DATE('"+event_start+"','YYYY-MM-DD' ) >=TO_DATE(e.event_start,'YYYY-MM-DD' )";
			if(event_end!=null)
				clause=clause+ " and TO_DATE('"+event_start+"','YYYY-MM-DD' ) <=TO_DATE(e.event_end,'YYYY-MM-DD' )";
			if(id_salle!=null)
				clause=clause+ " and s.id_salle ='"+id_salle+"'";
			if(dep_id!=null)
				clause=clause+ " and s.dep_id ='"+dep_id+"'";
			
			Query query = (Query) entityManager.createNativeQuery("select e.id from \"event\" e left join employee em on e.employee_id  = em.id \n"
					+"left join salle s on s.id_salle =em.id_salle \n"
					+ "where e.event_title= '"+event_title+"'"  + clause
					);
			log.info("query ",query);
			System.out.println("query  "+query.getResultList());
			
			return  query.getResultList().size();
		} catch (Exception e) {
			return null;
		} finally {
			entityManager.clear();
			entityManager.close();
		}
	}

}
