package com.kaibacorp.bookservice.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class Cambio implements Serializable {

    private static final long serialVersionUID=1L;
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversion_factor;
    private BigDecimal convertedValue;
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
