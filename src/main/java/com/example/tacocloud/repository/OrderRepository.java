package com.example.tacocloud.repository;

import com.example.tacocloud.data.TacoOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
