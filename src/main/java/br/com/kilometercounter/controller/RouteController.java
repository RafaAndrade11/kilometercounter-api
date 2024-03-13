package br.com.kilometercounter.controller;

import br.com.kilometercounter.domain.Route;
import br.com.kilometercounter.dtos.RouteData;
import br.com.kilometercounter.repository.RouteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/route")
public class RouteController {

    @Autowired
    RouteRepository routeRepository;

    @PostMapping
    @Transactional
    public void createRoute (@RequestBody @Valid RouteData data) {
        routeRepository.save(new Route(data));
    }
}
