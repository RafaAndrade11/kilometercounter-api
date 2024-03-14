package br.com.kilometercounter.domain;

import br.com.kilometercounter.dtos.ClientDataUpdate;
import br.com.kilometercounter.dtos.ClientDataCreate;
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
    private String address;


    public Client(ClientDataCreate data) {
        this.name = data.name();
        this.address = data.address();
    }


    public void updateInfo(ClientDataUpdate data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.address() != null) {
            this.address = data.address();
        }
    }
}
