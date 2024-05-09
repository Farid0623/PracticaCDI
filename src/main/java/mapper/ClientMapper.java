package mapper;

import mapper.dto.ClientDto;
import model.Client;

public class ClientMapper {
    public static ClientDto mapFromModel (Client client) {
        return new ClientDto(client.getClientId_Card(), client.getClient_Name(), client.getClientAge());
    }
    public static Client mapFromDTO (ClientDto client) {
        return Client.builder()
                .clientId_Card(client.clientId_Card())
                .client_Name(client.client_Name())
                .clientAge(client.client_Name())
                .build();
    }
}