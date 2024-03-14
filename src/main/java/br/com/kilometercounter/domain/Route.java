package br.com.kilometercounter.domain;

import br.com.kilometercounter.dtos.RouteDataCreate;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name ="route")
@Entity(name = "Route")
@Getter
@Setter
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

    public Route(RouteDataCreate data) {
        this.originClient = data.originClient();
        this.destinationClient = data.destinationClient();
        this.distance = data.distance();
        this.routeDate = data.routeDate();
    }
}
