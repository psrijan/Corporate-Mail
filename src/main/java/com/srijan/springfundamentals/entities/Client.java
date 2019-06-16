package com.srijan.springfundamentals.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "CLIENT")
public class Client {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 22)
    private Long id;
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 200)
    private String name;
    @Basic(optional = true)
    @Column(name = "EMAIL_ADDRESS", nullable = true, length = 200)
    private String emailAddress;
    @Basic(optional = false)
    @Column(name = "ACTIVE", nullable = false)
    private char active;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="BIRTHDAY" , nullable = false)
    private Date birthday;
    @Column(name = "RELATION" , nullable = false)
    private String relation;
    @JoinColumn(name = "PROFILE_ID" ,  referencedColumnName = "ID" )
    @ManyToOne (optional = false ,fetch =  FetchType.EAGER)
    private Profile profile;

    public Client() {

    }

    public Client(Long id) {
        this.id = id;
    }
}
