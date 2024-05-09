package mapping.mappers;

import mapping.dto.dto.ClientDTO;
import model.Client;

public class ClientMapper {
    public static ClientDTO mapFromModel (Client client) {
        return new ClientDTO(client.getClientId_Card(), client.getClient_Name(), client.getClientAge());
    }
    public static Client mapFromDTO (ClientDTO client) {
        return Client.builder()
                .clientId_Card(client.())
                .client_Name(client.client_Name())
                .clientAge(client.client_Name())
                .build();
    }
}