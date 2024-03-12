package br.com.kilometercounter.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name ="route")
@Entity(name = "Route")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_client_id")
    private Client originClient;

    @ManyToOne
    @JoinColumn(name = "destination_client_id")
    private Client destinationClient;

    private double distance;
    private LocalDate routeDate;
}
