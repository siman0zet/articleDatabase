package ru.pnu.edu.articledatabase.entity;

import javax.persistence.*;

public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "magazine_name")
    private String name;

    @Column(name = "link_on_page")
    private String linkOnPage;

    @Column(name = "email_addresses")
    private String EmailAddresses;

    @JoinColumn(name = "release_id")
    private Release release_id;

    @Column(name = "page_range")
    private String pageRange;
}
