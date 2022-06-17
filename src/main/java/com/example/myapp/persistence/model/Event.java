package com.example.myapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Event {
    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName ="event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private Long id;
    @Column(name = "event_title")
    private String title;

    @Column(name = "event_start")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String start;

    @Column(name = "event_end")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String end;


    @Column(name = "employee")
    private String employee;

    @Column(name="rrule")
    private String rrule;

    @Column(name="description")
    private String description;

    @Column(name="color")
    private String color;

    @Column(name="type")
    private String type;

    @Column(name="frequence")
    private String frequency;


    @Column(name = "employee_id")
    private long employeeid;
//    @Column(name="weekday")
//    private String weekday;
//
//    @Column(name="monthday")
//    private String monthday;
//
//    @Column(name="everyNmonth")
//    private Long everyNmonth;
//
//    @Column(name="everyNday")
//    private Long everyNday;
}