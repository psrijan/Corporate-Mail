package com.srijan.springfundamentals.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "FESTIVAL")
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME" , nullable = false)
    private String name;

    @Column(name = "DATE" , nullable = false)
    private String date;

    @Column(name= "CODE" , nullable =  false)
    private String code;

    @Column(name = "LOGO_URL" , nullable = false , length = 1000)
    private String logoUrl;

    @Column(name = "SUBJECT" , nullable = false , length = 2000)
    private String subject;

    @ManyToOne()
    @JoinColumn(name = "FEST_GROUP_ID" , nullable = false , referencedColumnName = "ID" )
    private FestivalGroup festivalGroup;
}
