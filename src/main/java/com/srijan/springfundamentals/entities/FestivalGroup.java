package com.srijan.springfundamentals.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "FEST_GROUP")
public class FestivalGroup {

    @Id
    @Column(name = "ID", nullable = false, precision = 38)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TYPE")
    private String type;

    @Basic(optional = false)
    @Column(name = "ACTIVE", nullable = false)
    private Character active;

    @Basic(optional = false)
    @OneToMany(mappedBy = "festivalGroup" )
    private List<Festival> festivalList;

    public FestivalGroup(Long id ) {
        this.id=id;
    }

    public FestivalGroup() {
    }
}
