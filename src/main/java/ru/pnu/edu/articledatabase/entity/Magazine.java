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
@Table(name = "magazine")
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "link_on_page")
    private String link_on_page;

    @Column(name = "email_addresses")
    private String email_addresses;

    @ManyToOne
    @JoinColumn(name = "release_id")
    private Release release_id;

    @Column(name = "page_range")
    private String page_range;

    @Column(name = "conference_date")
    @Temporal(TemporalType.DATE)
    private Date conference_date;
}

