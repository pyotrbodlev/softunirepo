package org.softuni.springdataadvancedquering.bookshopsystemapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.ConnectionBuilder;
import java.util.Scanner;

@Configuration
public class JavaProjectConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

}
