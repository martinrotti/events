package com.sagant.events.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "address", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Long id;

    @Column(name = "address", nullable = false, length = Integer.MAX_VALUE)
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    /*@JoinTable(
            name = "user_address", schema = "public",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))*/
    Set<UserEntity> users;

    public AddressEntity(String address) {
        this.address = address;
    }
}