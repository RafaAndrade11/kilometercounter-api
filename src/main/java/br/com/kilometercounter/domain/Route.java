package br.com.kilometercounter.domain;

import br.com.kilometercounter.dtos.route.RouteDataCreate;
import br.com.kilometercounter.dtos.route.RouteDataUpdate;
import br.com.kilometercounter.service.getDistance;
import jakarta.persistence.*;
import lombok.*;

import java.io.IOException;
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

    private Double distance;
    private LocalDate routeDate;

    public Route(RouteDataCreate data) {
        this.originClient = data.originClient();
        this.destinationClient = data.destinationClient();
        this.distance = data.distance();
        this.routeDate = data.routeDate();
    }

    public Route(Client originClient, Client destinationClient, double distance) {
        this.originClient = originClient;
        this.destinationClient = destinationClient;
        try {
            Long originAddress = originClient.getCep();
            Long destinationAddress = destinationClient.getCep();
            this.distance = getDistance.getData(originAddress, destinationAddress);
        } catch (IOException | InterruptedException e) {
            // Trate a exceção adequadamente, se necessário
            e.printStackTrace();
            this.distance = 0.0; // Ou outro valor padrão, dependendo do seu caso
        }
    }

    public void updateRouteInfo(RouteDataUpdate data) {
        if(data.destinationClient() != null) {
            this.destinationClient = data.destinationClient();
        }
        if(data.originClient() != null) {
            this.originClient = data.originClient();
        }
        if(data.distance() != null) {
            this.distance = data.distance();
        }
    }
}
