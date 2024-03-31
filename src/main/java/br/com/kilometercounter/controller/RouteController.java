package br.com.kilometercounter.controller;

import br.com.kilometercounter.domain.Client;
import br.com.kilometercounter.domain.Route;
import br.com.kilometercounter.dtos.route.RouteDataCreate;
import br.com.kilometercounter.dtos.route.RouteDataList;
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
import java.util.stream.Collectors;

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
            System.out.println("Erro ao buscar cliente");
            Client originClient = clientRepository.findById(data.originClient().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente de origem não encontrado"));
            Client destinationClient = clientRepository.findById(data.destinationClient().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente de destino não encontrado"));

            double distance = getDistance.getData(originClient.getCep(), destinationClient.getCep());

            Route route = new Route(originClient, destinationClient, distance);
            routeRepository.save(route);
            return ResponseEntity.ok("Rota criada com sucesso");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            System.out.println("erro aqui");
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
