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
        try {
            ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
            if (errorMap != null) return errorMap;

            coordinatesService.checkCoordinates(address);
            service.save(address);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Address com ID: '" + id + "' foi deletado", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSById(@PathVariable String id) {
        Optional<Address> address;
        try {
            address = service.findById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Address> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") String id, @Valid @RequestBody Address address,
                                    BindingResult result) {
        try {
            if (service.findById(id).isEmpty()) {
                return new ResponseEntity<>("id = " + id, HttpStatus.NOT_FOUND);
            }

            ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
            if (errorMap != null) return errorMap;

            address.setId(id);
            coordinatesService.checkCoordinates(address);
            service.save(address);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

}
