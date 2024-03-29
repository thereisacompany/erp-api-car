package com.erp.car;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CarServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CarServerApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
    }
}