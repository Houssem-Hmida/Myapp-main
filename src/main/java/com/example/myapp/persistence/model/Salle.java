package com.example.myapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Salle {
    @SequenceGenerator(
            name = "salle_sequence",
            sequenceName ="salle_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "salle_sequence"
    )
    @Id
    @Column(name="Id_salle")
    private Long id;
    @Column(name="Type")
    private String type;
    @Column(name="Nom")
    private String nom;
    @Column(name="Desks_number")
    private int nbposte;
    @Column(name="Attendance_percentage")
    private int pourcentagePres;
    @Column(name="Departement")
    private String dep;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_creation")
    private Timestamp datecreation;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_update")
    private Timestamp dateupdate;

    @ManyToOne
    @JoinColumn(name = "dep_id", insertable = false, updatable = false)
    private Departement departement ;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SALLE")
    private Set<Employee> employees;

    public Salle(String type, String nom, int nbposte, int pourcentagePres, String dep) {
        this.type = type;
        this.nom = nom;
        this.nbposte = nbposte;
        this.pourcentagePres = pourcentagePres;
        this.dep=dep;

    }
}
