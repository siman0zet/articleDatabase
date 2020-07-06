package ru.pnu.edu.articledatabase.entity;

import javax.persistence.*;
import java.sql.Date;

public class ExportControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date date;

    @Column
    private Integer number;
}
