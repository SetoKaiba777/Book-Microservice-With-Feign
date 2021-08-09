package com.kaibacorp.cambioservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "cambio")
public class Cambio implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_currency",nullable = true,length = 3)
    private String from;

    @Column(name = "to_currency",nullable = false,length = 3)
    private String to;

    @Column(nullable = false)
    private BigDecimal conversion_factor;

    @Transient
    private BigDecimal convertedValue;

    @Transient
    private String environment;

    public Cambio(){
    }
    public Cambio(Long id, String from, String to, BigDecimal conversion_factor,
                  BigDecimal convertedValue, String environment){
        this.id = id;
        this.environment=environment;
        this.from=from;
        this.to=to;
        this.conversion_factor=conversion_factor;
        this.convertedValue=convertedValue;

    }

}
