package br.com.kilometercounter.controllers;

import br.com.kilometercounter.controller.ClientController;
import br.com.kilometercounter.domain.Client;
import br.com.kilometercounter.repository.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
public class ClientTests {

    @Autowired
    private ClientController clientController;

    @Autowired
    private ClientRepository clientRepository;

    @Configuration
    static class TestConfig {
        @Bean
        public Client getClient() {
            Client client = new Client();

            client.setId(222l);
            client.setName("Loja teste");
            client.setAddress("Rua Amazonas, 231");

            return client;
        }
    }

    @Autowired
    private Client client;

    @Test
    public void createClient() {
        //to do
    }


}
