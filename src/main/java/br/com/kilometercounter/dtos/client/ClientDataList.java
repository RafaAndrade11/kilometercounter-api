package br.com.kilometercounter.dtos.client;

import br.com.kilometercounter.domain.Client;

public record ClientDataList(Long id, String name, String cep) {


    public ClientDataList(Client client) {
        this(client.getId(), client.getName(), client.getCep());
    }
}
