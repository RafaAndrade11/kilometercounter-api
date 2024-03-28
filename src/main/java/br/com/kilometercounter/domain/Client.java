package br.com.kilometercounter.domain;

import br.com.kilometercounter.dtos.client.ClientDataUpdate;
import br.com.kilometercounter.dtos.client.ClientDataCreate;
import jakarta.persistence.*;
import lombok.*;

@Table(name ="client")
@Entity(name = "Client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long cep;


    public Client(ClientDataCreate data) {
        this.name = data.name();
        this.cep = data.cep();
    }


    public void updateInfo(ClientDataUpdate data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.cep() != null) {
            this.cep = data.cep();
        }
    }
}
