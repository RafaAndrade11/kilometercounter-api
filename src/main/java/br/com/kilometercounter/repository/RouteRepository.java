package br.com.kilometercounter.repository;

import br.com.kilometercounter.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
