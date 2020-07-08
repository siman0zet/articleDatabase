package ru.pnu.edu.articledatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@Table(name = "open_publication")
public class OpenPublication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column
    private Integer number;
}
