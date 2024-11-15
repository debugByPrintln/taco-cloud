package com.example.tacocloud.repository;

import com.example.tacocloud.data.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

    Users findByUsername(String username);
}
