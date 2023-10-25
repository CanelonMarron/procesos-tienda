package com.procesos.tienda.controller;

import com.procesos.tienda.model.Address;
import com.procesos.tienda.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id){
        return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.OK);
    }

    @GetMapping("address")
    public ResponseEntity<List<Address>> findAll(){
        return new ResponseEntity<>(addressService.finAllAddress(),HttpStatus.OK);
    }


    @PostMapping("address")
    public ResponseEntity<Address> create(@Validated @RequestBody Address address,@PathVariable Long id){
        return new ResponseEntity<>(addressService.createAddress(address,id),HttpStatus.CREATED);
    }

    @GetMapping("address/disabled/{id}")
    public ResponseEntity disabled(@PathVariable Long id){
        return new ResponseEntity(addressService.updateStatusAddress(id),HttpStatus.OK);
    }
}
