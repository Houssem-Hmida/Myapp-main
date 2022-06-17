package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    @Query("select d from Event d where d.id=:id ")
    public Event findEventById(@Param("id") Long id);

    @Query("select d from Event d where d.title=:title ")
    public Event findEventByTitle(@Param("title") String title);

    public List<Event> findAllByOrderByIdAsc();
    
    
    
    
    @Query(value="select count(e.*) from \"event\" e left join employee em on e.employee_id  = em.id  \n"
    		+ "left join salle s on s.id_salle =em.id_salle \n"
    		+ "where TO_DATE(:event_end,'YYYY-MM-DD' ) <TO_DATE(e.event_end,'YYYY-MM-DD' ) and TO_DATE(:event_start,'YYYY-MM-DD' ) >=\n"
    		+ "TO_DATE(e.event_start,'YYYY-MM-DD' ) and s.id_salle =:id_salle and s.dep_id =:dep_id and e.event_title =:event_title", nativeQuery = true)
	public Long getStatics(@Param(value="event_start") String event_start, @Param(value="event_end") String event_end,
			@Param(value="id_salle") Long id_salle, @Param(value="dep_id") Long dep_id	, @Param(value="event_title") String event_title );
	

    
}
