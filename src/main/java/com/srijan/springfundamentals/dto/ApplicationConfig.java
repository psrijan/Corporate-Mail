package com.srijan.springfundamentals.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "APPLICATION_CONFIG")
public class ApplicationConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 22)
    private Long id;
    @Basic(optional = false)
    @Column(name = "CONFIG_KEY", nullable = false, length = 50)
    private String configKey;
    @Column(name = "CONFIG_VALUE", length = 500)
    private String configValue;

    public ApplicationConfig() {
    }

    public ApplicationConfig(Long id) {
        this.id = id;
    }

}
