package br.com.kilometercounter.controller;

import br.com.kilometercounter.service.getDistance;
import br.com.kilometercounter.domain.Client;
import br.com.kilometercounter.dtos.client.ClientDataCreate;
import br.com.kilometercounter.dtos.client.ClientDataUpdate;
import br.com.kilometercounter.repository.ClientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createClient (@RequestBody @Valid ClientDataCreate data) {
        clientRepository.save(new Client(data));

        return ResponseEntity.ok("Success");
    }

    @GetMapping
    public ResponseEntity<?> findAllClients() {
        List<Client> getClients = clientRepository.findAll();

        return new ResponseEntity(getClients, HttpStatus.OK);

    }

    @PutMapping
    @Transactional
    public ResponseEntity updateClient (@RequestBody @Valid ClientDataUpdate data) {
        var client = clientRepository.getReferenceById(data.id());
        client.updateInfo(data);

        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    //get para calculo da distancia
    @GetMapping("/distance/{sourceCEP}/{destinationCEP}")
    public ResponseEntity<String> calculateDistance(@PathVariable String sourceCEP, @PathVariable String destinationCEP) {
        try {
            getDistance.getData(sourceCEP, destinationCEP);
            return ResponseEntity.ok("Distance calculated successfully");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to calculate distance");
        }
    }

}

