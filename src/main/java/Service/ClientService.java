package Service;


import mapping.dto.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> list();
    ClientDTO byId(int id);
    void update(ClientDTO t);
    void delete(int id);
}
