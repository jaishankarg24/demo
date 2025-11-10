package com.example.demo.services;

import com.example.demo.entities.Address;
import com.example.demo.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public void showAddressById(Long id){
        Address address = addressRepository.findById(id).orElseThrow();
        System.out.println(address.getCity());
    }
}
