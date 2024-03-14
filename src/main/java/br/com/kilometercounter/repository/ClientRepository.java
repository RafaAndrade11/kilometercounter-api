package br.com.kilometercounter.repository;

import br.com.kilometercounter.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Arrays;

public interface ClientRepository extends JpaRepository <Client, Long> {

}
