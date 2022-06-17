package com.example.myapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;


@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project {

    @SequenceGenerator(
            name = "projet_sequence",
            sequenceName ="projet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "projet_sequence"
    )
    @Id
    @Column(name="ID_PROJET", unique = true, nullable = false)
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Priority")
    private int priority;
    @Column(name = "Description")
    private String description;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Start_date")
    private LocalDate startdate;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "End_date")
    private LocalDate enddate;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Creation_date")
    private Timestamp creationdate;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Update_date")
    private Timestamp updatedate;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROJECT")
    private Set<Employee> employees;

    public Project(String name, int priority, String description, LocalDate startdate, LocalDate enddate) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.startdate = startdate;
        this.enddate = enddate;
    }
}
