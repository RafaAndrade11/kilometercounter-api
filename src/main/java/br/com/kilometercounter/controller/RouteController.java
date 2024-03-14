package br.com.kilometercounter.controller;

import br.com.kilometercounter.domain.Route;
import br.com.kilometercounter.dtos.RouteDataCreate;
import br.com.kilometercounter.dtos.RouteDataList;
import br.com.kilometercounter.dtos.RouteDataUpdate;
import br.com.kilometercounter.repository.RouteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createRoute (@RequestBody @Valid RouteDataCreate data) {
        routeRepository.save(new Route(data));
    }

    @GetMapping
    public List<RouteDataList> findAllRoutes(){
        List<Route> routes = routeRepository.findAllById(List.of());
        return routes.stream()
                .map(RouteDataList::new)
                .collect(Collectors.toList());
    }

    @PutMapping
    @Transactional
    public void updateRoute (@RequestBody @Valid RouteDataUpdate data) {
        var route = routeRepository.getReferenceById(data.id());
        route.updateRouteInfo(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteRoute (@PathVariable Long id) {
        routeRepository.deleteById(id);
    }
}
