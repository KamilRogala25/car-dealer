package com.example.cardealer.controller;

import com.example.cardealer.enums.City;
import com.example.cardealer.model.Car;
import com.example.cardealer.model.Dealer;
import com.example.cardealer.repository.DealerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class DealerController {

    private DealerRepository dealerRepository;

    public DealerController(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    @GetMapping("dealers")
    public Iterable<Dealer> findAll() {
        return dealerRepository.findAll();
    }

    @PostMapping("dealers")
    public Dealer createDealer(@RequestParam(required = false) String name,
                               @RequestParam(required = false) City city) {
        Dealer dealer = new Dealer();
        if (name != null) {
            dealer.setName(name);
        }
        if (city != null) {
            dealer.setCity(city);
        }
        return dealerRepository.save(dealer);


    }

    @PutMapping("dealers/{id}")
    public ResponseEntity<Dealer> updateDealer(@PathVariable Integer id, @RequestParam(required = false) String name,
                                               @RequestParam(required = false) City city) {
        Optional<Dealer> dealerOptional = dealerRepository.findById(id);
        if (dealerOptional.isPresent()) {
            Dealer dealer = new Dealer();
            if (name != null) {
                dealer.setName(name);
            }
            if (city != null) {
                dealer.setCity(city);
            }
            return new ResponseEntity<>(dealerRepository.save(dealer), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("dealers/{id}")
    public ResponseEntity<Dealer> deleteDealer(@PathVariable Integer id){
        Optional<Dealer> dealerOptional = dealerRepository.findById(id);
        if (dealerOptional.isPresent()){
            dealerRepository.delete(dealerOptional.get());
            return new  ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("dealers/{id}/cars")
    public Iterable<Car> getCarsFromDealer(@PathVariable Integer id){
        Dealer dealer = dealerRepository.findById(id).get();
        return dealer.getCars();
    }

}
