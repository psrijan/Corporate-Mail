package com.srijan.springfundamentals.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PROFILE")
public class Profile {

    private static final long serialVersionUID = 1L;

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
    @ManyToMany()
    @JoinTable(name = "PROFILE_SERVICE" ,
            joinColumns = {@JoinColumn(name = "PROFILE_ID", nullable = false, updatable = false) },
            inverseJoinColumns = {@JoinColumn(name = "SERVICE_ID", nullable = false, updatable = false) })
    private List<Service> serviceList;

    @Basic(optional = false)
    @ManyToMany()
    @JoinTable(name = "PROFILE_FESTIVAL" ,
            joinColumns = {@JoinColumn(name = "PROFILE_ID", nullable = false, updatable = false) },
            inverseJoinColumns = {@JoinColumn(name = "FEST_GROUP_ID", nullable = false, updatable = false) })
    private List<FestivalGroup> festivalGroupList;


    public Profile(Long id ) {
        this.id=id;
    }

    public Profile() {
    }

}
