package com.sagant.events.services;

import com.sagant.events.dtos.AddressDto;
import com.sagant.events.dtos.UserDto;
import com.sagant.events.entities.AddressEntity;
import com.sagant.events.entities.UserEntity;
import com.sagant.events.enums.EEntityType;
import com.sagant.events.activemq.Event;
import com.sagant.events.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Service para el manejo de usuarios de la aplicación.
 * Provee los métodos CRUD
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddressService addressService;
    private final EventService eventService;

    private UserService(UserRepository userRepository, AddressService addressService, EventService eventService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.eventService = eventService;
    }

    public UserDto getUserById(Long id) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(id);
        return optionalUserEntity.map(userEntity -> new UserDto(
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getEmail()
        )).orElse(null);
    };

    public void createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity(
                userDto.name(),
                userDto.surname(),
                userDto.email()
        );
        this.userRepository.save(userEntity);
        Event event = new Event("C", "Creación de un nuevo usuario", LocalDate.now(), EEntityType.CREATE, userEntity.getUserId());
        this.eventService.generateEvent(event);
    };

    public UserDto updateUser(Long id, UserDto userDto) {
        this.userRepository.findById(id).ifPresent(userEntity -> {
            userEntity.setName(userDto.name());
            userEntity.setSurname(userDto.surname());
            userEntity.setEmail(userDto.email());
            this.userRepository.save(userEntity);
            Event event = new Event("U", "Actualización de un usuario", LocalDate.now(), EEntityType.UPDATE, userEntity.getUserId());
            this.eventService.generateEvent(event);
        });
        return userDto;
    }

    public void deleteUser(Long id) {
        this.userRepository.findById(id).ifPresent(userEntity -> {
            this.userRepository.delete(userEntity);
            Event event = new Event("D", "Eliminación de un usuario", LocalDate.now(), EEntityType.DELETE, userEntity.getUserId());
            this.eventService.generateEvent(event);
        });

    }

    /***
     * Agrega una dirección a la base de datos y la asocia al usuario con el user_id pasado por parametro
     * @param userId identificador del usuario
     * @param addressDto datos de la dirección nueva
     */
    public void addAddressToUser(Long userId, AddressDto addressDto) {
        this.userRepository.findById(userId).ifPresent(userEntity -> {
            AddressEntity addressEntity = this.addressService.createAddress(addressDto);
            Set<AddressEntity> addressEntities = new HashSet<>();
            addressEntities.add(addressEntity);
            userEntity.setAddresses(addressEntities);
            this.userRepository.save(userEntity);
            Event event = new Event("A", "Alta de una nueva dirección", LocalDate.now(), EEntityType.ADDRESS, userEntity.getUserId());
            this.eventService.generateEvent(event);
        });

    }
}
