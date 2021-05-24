package br.com.stoom.demo.repository;

import br.com.stoom.demo.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends CrudRepository<Address, String> {

}
