package com.example.cardealer.repository;

import com.example.cardealer.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {
}
