package br.com.kilometercounter.controller;

import br.com.kilometercounter.domain.Client;
import br.com.kilometercounter.dtos.ClientData;
import br.com.kilometercounter.repository.ClientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    @Transactional
    public void createClient (@RequestBody @Valid ClientData data) {
        clientRepository.save(new Client(data));
    }
}
