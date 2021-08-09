package com.kaibacorp.bookservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="author", nullable = false,length = 180)
    private String author;

    @Column(name="launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date launchDate;


    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false)
    private BigDecimal price;

    @Transient
    private String currency;

    @Transient
    private String environment;

    public Book(){}

    public Book(Long id, String author, Date launchDate, String title,
            BigDecimal price, String currency, String environment){
        this.id = id;
        this.author=author;
        this.launchDate=launchDate;
        this.title=title;
        this.price=price;
        this.currency=currency;
        this.environment=environment;
    }
}
