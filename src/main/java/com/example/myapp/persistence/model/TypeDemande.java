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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class TypeDemande implements Serializable {
    @Id
    @SequenceGenerator(
            name = "typeDemande_sequence",
            sequenceName ="typeDemande_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "typeDemande_sequence"
    )
    private Long id;

    @Column(name = "Type")
    private String type;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Creation_date")
    private Timestamp creationdate;



    @OneToMany
@   JoinColumn(name = "type_id", insertable = false, updatable = false)
    private Set<Demande> demandes;

    public TypeDemande(String type) {
        this.type = type;
    }


}
