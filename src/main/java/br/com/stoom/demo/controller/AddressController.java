package br.com.stoom.demo.controller;

import br.com.stoom.demo.domain.Address;
import br.com.stoom.demo.service.AddressService;
import br.com.stoom.demo.service.CoordinatesService;
import br.com.stoom.demo.service.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

//@CrossOrigin
@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private AddressService service;
    private MapValidationErrorService mapValidationErrorService;
    private CoordinatesService coordinatesService;

    public AddressController(AddressService service, CoordinatesService coordinatesService
            , MapValidationErrorService mapValidationErrorService) {
        this.service = service;
        this.coordinatesService = coordinatesService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody Address address, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        coordinatesService.checkCoordinates(address);
        service.save(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.findById(id);
        service.delete(id);
        return new ResponseEntity<>("Address com ID: '" + id + "' foi deletado", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSById(@PathVariable String id) {
        Address address = service.findById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Address> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") String id, @Valid @RequestBody Address address,
                                    BindingResult result) {

        service.findById(id);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        address.setId(id);
        coordinatesService.checkCoordinates(address);
        service.save(address);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

}
