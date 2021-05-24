package br.com.stoom.demo.service;

import br.com.stoom.demo.client.dto.GoogleResult;
import br.com.stoom.demo.domain.Address;
import br.com.stoom.demo.exception.AddressNotFound;
import br.com.stoom.demo.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public Address save(Address address) {
        return repository.save(address);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Address findById(String id) {
        return repository.findById(id).orElseThrow(() -> new AddressNotFound(id));
    }

    public Iterable<Address> findAll() {
        return repository.findAll();
    }


}
