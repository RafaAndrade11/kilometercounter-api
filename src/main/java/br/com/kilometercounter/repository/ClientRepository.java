package br.com.kilometercounter.repository;

import br.com.kilometercounter.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client, Long> {
}
