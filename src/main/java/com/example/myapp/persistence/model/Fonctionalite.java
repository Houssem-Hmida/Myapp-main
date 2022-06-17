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
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Fonctionalite implements Serializable {
    @SequenceGenerator(
            name = "fonctionalite_sequence",
            sequenceName ="fonctionalite_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fonctionalite_sequence"
    )
    @Id
    @Column(name="ID_FONCTIONALITE", unique = true, nullable = false)
    private Long id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Designation")
    private String designation;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Creation_date")
    private Timestamp creationdate;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Update_date")
    private Timestamp updatedate;

    @ManyToMany(fetch = EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Profil> profils = new ArrayList<>();

    public Fonctionalite(String name, String designation) {
        this.name = name;
        this.designation = designation;
    }

}
