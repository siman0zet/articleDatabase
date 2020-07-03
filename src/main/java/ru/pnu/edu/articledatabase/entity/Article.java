package ru.pnu.edu.articledatabase.entity;

import javax.persistence.*;


@Entity
@Table
public class Article {
    @Column(name = "name_article")
    private String name;

    @Column(name = "study_year")
    private Integer studyYear;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Article() {

    }

    public Article(String name, Integer studyYear, Integer id) {
        this.name = name;
        this.studyYear = studyYear;
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getStudyYear(){
        return studyYear;
    }

    public void setStudyYear(Integer studyYear){
        this.studyYear = studyYear;
    }

}
