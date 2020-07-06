package ru.pnu.edu.articledatabase.entity;


import javax.persistence.*;
import java.sql.Date;


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
    private Date departure_date;

    @Column
    private String notes;

    @Column
    private Double price;

    @JoinColumn(name = "open_publication_id")
    private OpenPublication open_publication_id;

    @JoinColumn(name = "export_publication_id")
    private ExportControl export_control_id;



    public Article() {

    }

    public Article(String name, Integer studyYear, Integer id) {
        this.name = name;
        this.studyYear = studyYear;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(Integer studyYear) {
        this.studyYear = studyYear;
    }

}
