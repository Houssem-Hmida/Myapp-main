package com.example.myapp.persistence.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="log_data")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogData {

    @SequenceGenerator(
            name = "logData_sequence",
            sequenceName ="logData_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "logData_sequence"
    )
    @Id
    @Column(name = "ID_LOG_DATA", unique = true, nullable = false)
    private Long id;

    @Column(name = "USERNAME", nullable = false, length = 255)
    private String username;

    /*@Column(name = "ROLE", nullable = false, length = 255)
    private String role;
*/
    @Column(name = "ACTION", nullable = false, length = 50)
    private String action;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy HH:mm:ss")
    @Column(name = "DATE_ACTION")
    private LocalDateTime dateAction;

}