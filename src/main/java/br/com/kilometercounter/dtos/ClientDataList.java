package br.com.kilometercounter.dtos;

import br.com.kilometercounter.domain.Client;

public record ClientDataList(Long id, String name, String address) {


    public ClientDataList(Client client) {
        this(client.getId(), client.getName(), client.getAddress());
    }
}