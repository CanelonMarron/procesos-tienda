package com.procesos.tienda.service;

import com.procesos.tienda.exception.NotFoundException;
import com.procesos.tienda.model.Address;
import com.procesos.tienda.model.User;
import com.procesos.tienda.repository.AddressRepository;
import com.procesos.tienda.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserService userService;

    public Address createAddress(Address address, Long idUser){
        User userBd = userService.getUserById(idUser);
        address.setUser(userBd);
        return addressRepository.save(address);
    }

    public Address updateStatusAddress(Long id){
        Optional<Address> addressBd = addressRepository.findById(id);
        if(addressBd.isEmpty()){
            throw new NotFoundException(Constants.ADDRESS_NOT_FOUND.getMessage());
        }
        addressBd.get().setStatus(Boolean.FALSE);
        return  addressRepository.save(addressBd.get());
    }

    public List<Address> finAllAddress(){
        return  (List<Address>) addressRepository.findAll();
    }

    public Address getAddressById(Long id){
        Optional<Address> addressBd = addressRepository.findById(id);
        if(addressBd.isEmpty()) {
            throw new NotFoundException(Constants.ADDRESS_NOT_FOUND.getMessage());
        }
        return addressBd.get();
    }
}
