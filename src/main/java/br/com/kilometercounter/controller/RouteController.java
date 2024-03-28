package br.com.kilometercounter.controller;

import br.com.kilometercounter.domain.Route;
import br.com.kilometercounter.dtos.route.RouteDataCreate;
import br.com.kilometercounter.dtos.route.RouteDataList;
import br.com.kilometercounter.dtos.route.RouteDataUpdate;
import br.com.kilometercounter.repository.RouteRepository;
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

    @PostMapping
    @Transactional
    public ResponseEntity createRoute(@RequestBody @Valid RouteDataCreate data) {

        routeRepository.save(new Route(data));

        return ResponseEntity.ok("Success");
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
