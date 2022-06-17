package com.example.myapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Demande implements Serializable {

    @Id
    @SequenceGenerator(
            name = "Demande_sequence",
            sequenceName ="Demande_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Demande_sequence"
    )
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @Column(name = "datedebut")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String datedebut;

    @Column(name = "datefin")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String datefin;

    @Column(name = "empid")
    private Long empid;

    @Column(name = "etat")
    private String etat;

    @Column(name = "empnom")
    private String empnom;

    @Column(name = "empprenom")
    private String empprenom;

    @Column(name = "priorite")
    private Long priorite;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Creation_date")
    private Timestamp creationdate;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Update_date")
    private Timestamp updatedate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Start_date")
    private LocalDate startdate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "End_date")
    private LocalDate enddate;

    //    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "etat_id", insertable = false, updatable = false)
    private EtatDemande etatDemande;

    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private TypeDemande typeDemande;

    @ManyToOne
    @JoinColumn(name = "emp_id", insertable = false, updatable = false)
    private Employee employee;

    public Demande(String title, String description, String datedebut, String datefin, Long empid) {
        this.title = title;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.empid = empid;
    }
}
