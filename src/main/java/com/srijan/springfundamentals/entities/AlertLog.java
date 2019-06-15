package com.srijan.springfundamentals.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ALERT_LOG")
public class AlertLog {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 22)
    private Long id;
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 200)
    private String name;
    @Column(name="DATE" , nullable = false)
    private Date date;
    @Basic(optional = false)
    @Column(name = "WISHED" , nullable = false , length = 1)
    private Character wished;
    @JoinColumn(referencedColumnName = "ID" ,name = "APPLICATION_USER_ID" , nullable = false)
    @ManyToOne(optional = false)
    private ApplicationUser applicationUser;
    @JoinColumn(referencedColumnName = "ID" , name = "FRIEND_ID" , nullable = false)
    @ManyToOne
    private Client friend;
    @Column(name = "YEAR" , nullable = false)
    private String year;

}
