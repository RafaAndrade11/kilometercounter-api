package br.com.kilometercounter.controller;

import br.com.kilometercounter.domain.Client;
import br.com.kilometercounter.domain.Route;
import br.com.kilometercounter.dtos.route.RouteDataCreate;
import br.com.kilometercounter.dtos.route.RouteDataUpdate;
import br.com.kilometercounter.repository.ClientRepository;
import br.com.kilometercounter.repository.RouteRepository;
import br.com.kilometercounter.service.getDistance;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    ClientRepository clientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createRoute(@RequestBody @Valid RouteDataCreate data) {
        try {
            System.out.println("Iniciando criação de rota");
            System.out.println("ID do cliente de origem: " + data.originClient().getId());
            System.out.println("ID do cliente de destino: " + data.destinationClient().getId());

            Client originClient = clientRepository.findById(data.originClient().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente de origem não encontrado"));
            Client destinationClient = clientRepository.findById(data.destinationClient().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente de destino não encontrado"));

            System.out.println("Clientes encontrados: " + originClient.getName() + ", " + destinationClient.getName());

            double distance = getDistance.getData(originClient.getCep(), destinationClient.getCep());


            System.out.println("CEP DO ORIGIN CLIENT: " + originClient.getCep());
            System.out.println("CEP DO DESTINATION CLIENT: " + destinationClient.getCep());

            System.out.println("Distância calculada: " + distance);

            Route route = new Route(originClient, destinationClient, distance);
            routeRepository.save(route);

            System.out.println("Rota criada com sucesso");

            return ResponseEntity.ok("Rota criada com sucesso");
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (Exception e) {
            System.out.println("Erro ao calcular a distância: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar a rota");
        }
    }



    @GetMapping
    public ResponseEntity<?> findAllRoutes() {

        List<Route> getRoutes = routeRepository.findAll();

        return new ResponseEntity(getRoutes, HttpStatus.OK);

    }

    @PutMapping
    @Transactional
    public ResponseEntity updateRoute(@RequestBody @Valid RouteDataUpdate data) {

        var route = routeRepository.getReferenceById(data.id());
        route.updateRouteInfo(data);

        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteRoute(@PathVariable Long id) {

        routeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
