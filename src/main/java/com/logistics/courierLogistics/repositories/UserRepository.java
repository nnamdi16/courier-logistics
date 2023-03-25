package com.logistics.courierLogistics.repositories;

import com.logistics.courierLogistics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT EXISTS (SELECT u FROM users u WHERE u.email = :email)", nativeQuery = true)
    Boolean checkIfEmailExists(String email);
}
