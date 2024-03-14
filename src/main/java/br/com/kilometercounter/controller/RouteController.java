package br.com.kilometercounter.controller;

import br.com.kilometercounter.domain.Route;
import br.com.kilometercounter.dtos.RouteDataCreate;
import br.com.kilometercounter.repository.RouteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    RouteRepository routeRepository;

    @PostMapping
    @Transactional
    public void createRoute (@RequestBody @Valid RouteDataCreate data) {
        routeRepository.save(new Route(data));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteRoute (@PathVariable Long id) {
        routeRepository.deleteById(id);
    }
}
