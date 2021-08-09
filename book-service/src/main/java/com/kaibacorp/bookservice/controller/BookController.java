package com.kaibacorp.bookservice.controller;

import com.kaibacorp.bookservice.model.Book;
import com.kaibacorp.bookservice.proxy.CambioProxy;
import com.kaibacorp.bookservice.repository.BookRepository;
import com.kaibacorp.bookservice.response.Cambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CambioProxy cambioProxy;

    @GetMapping(value ="/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id,
                         @PathVariable("currency") String currency){
        var book = bookRepository.getById(id);
        if(book==null)
            throw new RuntimeException("Book don't found");
        var  cambio = cambioProxy.getCambio(book.getPrice(),"USD",currency);
        var port = environment.getProperty("local.server.port");

        book.setPrice(cambio.getConvertedValue());
        book.setEnvironment(port+"FEIGN");
        return book;
    }
}
//**    @GetMapping(value ="/{id}/{currency}")
//    public Book findBook(@PathVariable("id") Long id,
//                         @PathVariable("currency") String currency){
//        var book = bookRepository.getById(id);
//        if(book==null)
//            throw new RuntimeException("Book don't found");
//        HashMap<String,String> params = new HashMap<>();
//        params.put("amount",book.getPrice().toString());
//        params.put("to",currency);
//        params.put("from","USD" );
//        var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/"+
//                "{amount}/{from}/{to}", Cambio.class,params);
//
//        var port = environment.getProperty("local.server.port");
//        var cambio = response.getBody();
//        book.setPrice(cambio.getConvertedValue());
//        book.setEnvironment(port);
//        return book;
