package br.com.stoom.demo.service;


import br.com.stoom.demo.domain.Address;
import br.com.stoom.demo.repository.AddressRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class AddressServiceTest {

    @Mock
    private AddressRepository repository;

    @InjectMocks
    private AddressService addressService;
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


    @Test
    public void saveMustReturnNotNullTest() {
        when(repository.save(any())).thenReturn(this.address);
        Address address = addressService.save(this.address);
        Assert.assertNotNull(address);
    }

    @Test
    public void findMustReturnNotNullTest() {
        when(repository.findById(any())).thenReturn(Optional.of(this.address));
        Address address = addressService.findById(this.address.getId());
        Assert.assertNotNull(address);
    }

    @Test
    public void deleteMustReturnOneInvocationTest(){
        doNothing().when(repository).deleteById(any());
        addressService.delete(this.address.getId());
        verify(repository, times(1)).deleteById(this.address.getId());
    }

    @Test
    public void findAllMustReturnTrueTest() {
        when(repository.findAll()).thenReturn(List.of(this.address));
        Iterable<Address> list = addressService.findAll();
        Assert.assertTrue(list.iterator().hasNext());
    }

}
