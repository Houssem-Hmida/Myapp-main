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
import java.util.Set;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Utilisateur implements Serializable  {
    @SequenceGenerator(
            name = "utilisateur_sequence",
            sequenceName ="utilisateur_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "utilisateur_sequence"
    )
    @Id
    @Column(name="ID_UTILISATEUR", unique = true, nullable = false)
    private Long id;
    @Column(name = "USERNAME", unique = true, nullable = false, length = 50)
    private String userName;
    @Column(name = "PASSWORD", nullable = false)
    private String userPassword;
    @Column(name = "EMAIL")
    private String email;

    public static final String DIRECTORY = "C:/Users/HP/Desktop/pfe/src/assets/img/";


    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Creation_date")
    private Timestamp creationdate;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Update_date")
    private Timestamp updatedate;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "ID_EMP")
    private Employee employee;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Profil> profils;

    public Utilisateur(String userName, String userPassword, String email, Set<Profil> profils,Employee employee) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.profils = profils;
        this.employee=employee;
    }

}
