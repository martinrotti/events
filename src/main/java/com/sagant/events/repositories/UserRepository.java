package com.sagant.events.repositories;

import com.sagant.events.entities.AddressEntity;
import com.sagant.events.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM user u INNER JOIN user_address ua ON ua.user_id = user_id WHERE ua.user_id = ?1 ", nativeQuery = true)
    List<AddressEntity> findAllAddress(Long id);
}
