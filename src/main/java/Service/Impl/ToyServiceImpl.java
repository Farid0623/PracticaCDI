package Service.Impl;

import mapper.dto.ClientDto;
import mapping.dto.dto.ToyDTO;
import mapping.mappers.ClientMapper;
import mapping.mappers.ToyMapper;
import model.Client;
import model.Toy;
import repository.Repository;

import java.sql.SQLException;
import java.util.List;

public class ToyServiceImpl implements Service {

    private Repository<Toy> toyRepository;
    private Repository<Client> clientRepository;
    public ToyServiceImpl() {
        this.toyRepository = new ToyRepositoryJDBCImpl();
        this.clientRepository = new ClientRepositoryJDBCImpl();
    }


    @Override
    public void addToy(ToyDTO toyDTO) {
        toyRepository.save(ToyMapper.mapFromDto(toyDTO));
    }

    @Override
    public ToyDTO search(Integer id) throws SQLException {
        return ToyMapper.mapFromModel(toyRepository.byId(id));
    }


    @Override
    public void addClient(ClientDto clientDTO) {
        clientRepository.save(ClientMapper.mapFromDTO(clientDTO));
    }

    @Override
    public List<ClientDto> listClients() {
        return clientRepository.list()
                .stream()
                .map(ClientMapper::mapFromModel)
                .toList();
    }
    @Override
    public List<ToyDTO> listToys() {
        return toyRepository.list()
                .stream()
                .map(ToyMapper::mapFromModel)
                .toList();
    }

    @Override
    public ToyDTO searchByName(String name) throws SQLException {
        return ToyMapper.mapFromModel(toyRepository.byName(name));
    }
}
