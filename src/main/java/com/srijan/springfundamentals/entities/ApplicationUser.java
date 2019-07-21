package com.srijan.springfundamentals.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "APPLICATION_USER")
@Getter
@Setter
public class ApplicationUser extends Auditable<ApplicationUser> {

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
    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, length = 200)
    private String password;
    @Basic(optional = false)
    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;
    @JoinColumn(name = "PROFILE_ID" ,  referencedColumnName = "ID" )
    @ManyToOne (optional = false ,fetch =  FetchType.EAGER)
    private Profile profile;
    @OneToMany(mappedBy = "applicationUser" ,fetch = FetchType.EAGER)
    private List<Client> clientList;
    @Column(name = "credential" , nullable = false)
    private String credential;

    public ApplicationUser() {
    }

    public ApplicationUser(Long id) {
        this.id = id;
    }
}
