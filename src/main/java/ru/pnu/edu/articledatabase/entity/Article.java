package ru.pnu.edu.articledatabase.entity;


import javafx.scene.control.DatePicker;
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
public class Article{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(name = "study_year")
    private Integer study_year;

    @Column
    private String authors;

    @Column
    private String article_level;

    @Column
    private String stage;

    @ManyToOne
    @JoinColumn(name = "magazine_id")
    private Magazine magazine_id;

    @Column(name = "page_range")
    private String page_range;

    @Column
    private Double price;

    @Column(name = "publication_type")
    private String publication_type;

    @Column
    private Integer volume;

    @Column
    private Integer release;

    @Column
    @Temporal(TemporalType.DATE)
    private Date departure_date;

    @Column
    private String science_area;

    @ManyToOne
    @JoinColumn(name = "open_publication_id")
    private OpenPublication open_publication_id;

    @ManyToOne
    @JoinColumn(name = "export_publication_id")
    private ExportControl export_control_id;

}
