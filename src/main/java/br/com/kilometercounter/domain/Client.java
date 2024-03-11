package br.com.kilometercounter.domain;

import br.com.kilometercounter.dtos.ClientData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name ="client")
@Entity(name = "Client")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;


    public Client(ClientData data) {
        this.name = data.name();
        this.address = data.address();
    }
}
