package br.com.kilometercounter.controllers;

import br.com.kilometercounter.controller.ClientController;
import br.com.kilometercounter.domain.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class ClientTests {

    @Autowired
    private ClientController clientController;

    @Bean
    public Client getClient() {
        Client client = new Client();

        client.setId(222l);
        client.setName("Loja teste");
        client.setAddress("Rua Amazonas, 231");

        return client;
    }

    @Test
    public void createClient() {

    }
}
