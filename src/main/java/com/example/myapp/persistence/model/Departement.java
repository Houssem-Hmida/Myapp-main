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
import java.util.Set;

@Data
@Entity
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Departement implements Serializable {
    @Id
    @SequenceGenerator(
            name = "departement_sequence",
            sequenceName ="departement_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "departement_sequence"
    )
    private Long id;
    @Column(name = "Department_name")
    private String name;
    @Column(name = "Nombre_salle")
    private int nbsalles;

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
    @JoinColumn(name = "dep_id")
    private Set<Salle> Salles;

    public Departement(String name, int nbsalles) {
        this.name = name;
        this.nbsalles = nbsalles;
    }
}
