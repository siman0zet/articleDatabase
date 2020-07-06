package ru.pnu.edu.articledatabase.entity;

import javax.persistence.*;
import java.sql.Date;

public class OpenPublication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date date;

    @Column
    private Integer number;
}
