package ru.pnu.edu.articledatabase.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.BlobType;

import javax.persistence.*;
import java.util.Date;

@Builder
@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "article_name")
    private String name;

    @Column(name = "study_year")
    private Integer studyYear;

    @Column
    private String science_area;

    @ManyToOne
    @JoinColumn(name = "magazine_id")
    private Magazine magazine_id;

    @Column
    private String article_level;

    @Column
    private String publication_type;

    @Column
    private String stage;

    @Column
    private String authors;

    @Column
    private Integer volume;

    @Column
    @Temporal(TemporalType.DATE)
    private Date departure_date;

    @Column
    private String notes;

    @Column
    private Double price;

    @Column
    private BlobType article_file;

    @ManyToOne
    @JoinColumn(name = "open_publication_id")
    private OpenPublication open_publication_id;

    @ManyToOne
    @JoinColumn(name = "export_publication_id")
    private ExportControl export_control_id;


}
