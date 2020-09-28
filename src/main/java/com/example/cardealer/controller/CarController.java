package com.example.cardealer.controller;


import com.example.cardealer.enums.Color;
import com.example.cardealer.enums.Manufacturer;
import com.example.cardealer.enums.Model;
import com.example.cardealer.model.Car;
import com.example.cardealer.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class CarController {

    public CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("cars")
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    @GetMapping("cars/id={id}")
    public Car findById(@RequestParam Integer id) {
        return carRepository.findById(id).get();
    }

    @GetMapping("cars/?search={search}")
    public List<Car> findByQuote(@RequestParam String quote) {
        List<Car> results = new ArrayList<>();
        for (Car car : carRepository.findAll()) {
            if (car.getAge().toString().contains(quote) || car.getColor().toString().contains(quote)
                    || car.getEngineCapacity().toString().contains(quote) || car.getManufacturer().toString().contains(quote)
                    || car.getModel().toString().contains(quote) && !results.contains(car)) {
                results.add(car);
            }
        }
        return results;
    }

    @PostMapping("cars")
    public Car createCar(@RequestParam(required = false) Manufacturer manufacturer,
                         @RequestParam(required = false) Integer age, @RequestParam(required = false) Double engineCapacity,
                         @RequestParam(required = false) Model model, @RequestParam(required = false) Color color,
                         @RequestParam(required = false) Integer horsePower, @RequestParam(required = false) Boolean hasTurbo) {
        Car car = new Car();
        if (manufacturer != null) car.setManufacturer(manufacturer);
        if (age != null) car.setAge(age);
        if (engineCapacity != null) car.setEngineCapacity(engineCapacity);
        if (model != null) car.setModel(model);
        if (color != null) car.setColor(color);
        if (horsePower != null) car.setHorsePower(horsePower);
        if (hasTurbo != null) car.setHasTurbo(hasTurbo);
        return carRepository.save(car);
    }

    @PutMapping("cars/id={id}")
    public ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestParam(required = false) Manufacturer manufacturer,
                                         @RequestParam(required = false) Integer age, @RequestParam(required = false) Double engineCapacity,
                                         @RequestParam(required = false) Model model, @RequestParam(required = false) Color color,
                                         @RequestParam(required = false) Integer horsePower, @RequestParam(required = false) Boolean hasTurbo) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            if (manufacturer != null) {
                car.setManufacturer(manufacturer);
            }
            if (age != null) {
                car.setAge(age);
            }
            if (engineCapacity != null) {
                car.setEngineCapacity(engineCapacity);
            }
            if (model != null) {
                car.setModel(model);
            }
            if (color != null) {
                car.setColor(color);
            }
            if (horsePower != null) {
                car.setHorsePower(horsePower);
            }
            if (hasTurbo != null) {
                car.setHasTurbo(hasTurbo);
            }
            return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("cars/id={id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Integer id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            carRepository.delete(car.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

