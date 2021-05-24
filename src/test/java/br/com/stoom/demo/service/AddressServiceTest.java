package br.com.stoom.demo.service;


import br.com.stoom.demo.domain.Address;
import br.com.stoom.demo.repository.AddressRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressRepository repository;
    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setZipcode("88034-050");
        address.setStreetName("Rua Prof. Ayrton Roberto de Oliveira");
        address.setState("SC");
        address.setNumber(64);
        address.setNeighbourhood("Itacorubi");
        address.setCountry("Brasil");
        address.setCity("Florianópolis ");
    }

    @AfterEach
    void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void saveAndFindMustReturnNotEmptyTest() {
        Address address = repository.save(this.address);
        Assert.assertFalse(repository.findById(address.getId()).isEmpty());
    }

    @Test
    public void deleteAndFindMustReturnEmptyTest() {
        Address address = repository.save(this.address);
        Assert.assertNotNull(repository.findById(address.getId()));
        repository.deleteById(address.getId());
        Assert.assertTrue(repository.findById(address.getId()).isEmpty());
    }

    @Test
    public void findAllTest() {
        Address address = repository.save(this.address);
        Assert.assertNotNull(repository.findById(address.getId()));
        Assert.assertTrue(repository.findAll().iterator().hasNext());
    }

}
