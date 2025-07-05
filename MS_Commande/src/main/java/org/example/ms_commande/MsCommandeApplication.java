package org.example.ms_commande;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class MsCommandeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCommandeApplication.class, args);
    }

}
