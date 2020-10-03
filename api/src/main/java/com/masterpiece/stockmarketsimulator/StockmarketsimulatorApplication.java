package com.masterpiece.stockmarketsimulator;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StockmarketsimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockmarketsimulatorApplication.class, args);
    }


    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
/*
    @Bean
    public SimpleModule BatchDealCurrentPriceDeserializer(){
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Double.class, new BatchDeserializer());
        return module;
    }*/
}
