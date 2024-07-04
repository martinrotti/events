package com.sagant.events.controllers;

import com.sagant.events.dtos.AddressDto;
import com.sagant.events.dtos.UserDto;
import com.sagant.events.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        return ResponseEntity.ok().body(userService.updateUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/address/{id}")
    public ResponseEntity<?> addAddressToUser(@PathVariable Long id, @RequestBody AddressDto addressDto) {
        userService.addAddressToUser(id, addressDto);
        return ResponseEntity.ok().build();
    }
}
