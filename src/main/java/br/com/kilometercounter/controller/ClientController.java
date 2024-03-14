package br.com.kilometercounter.controller;

import br.com.kilometercounter.domain.Client;
import br.com.kilometercounter.dtos.ClientDataUpdate;
import br.com.kilometercounter.dtos.ClientDataCreate;
import br.com.kilometercounter.dtos.ClientDataList;
import br.com.kilometercounter.repository.ClientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    @Transactional
    public void createClient (@RequestBody @Valid ClientDataCreate data) {
        clientRepository.save(new Client(data));
    }

    @GetMapping
    public List<ClientDataList> findAllClients() {
        List<Client> clients = clientRepository.findAllById(List.of());
        return clients.stream()
                .map(ClientDataList::new)
                .collect(Collectors.toList());
    }

    @PutMapping
    @Transactional
    public void updateClient (@RequestBody @Valid ClientDataUpdate data) {
        var client = clientRepository.getReferenceById(data.id());
        client.updateInfo(data);
    }
}

