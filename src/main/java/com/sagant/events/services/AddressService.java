package com.sagant.events.services;

import com.sagant.events.dtos.AddressDto;
import com.sagant.events.entities.AddressEntity;
import com.sagant.events.repositories.AddressRepository;
import org.springframework.stereotype.Service;

/**
 * Service para el manejo de direcciones de los usuarios de la aplicación.
 */
@Service
public class AddressService {
    private final AddressRepository addressRepository;

    private AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressEntity createAddress(AddressDto addressDto) {
        AddressEntity addressEntity = new AddressEntity(addressDto.address());
        return this.addressRepository.save(addressEntity);
    };

}
