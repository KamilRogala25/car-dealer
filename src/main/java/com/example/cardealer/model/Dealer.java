package com.example.cardealer.model;

import com.example.cardealer.enums.City;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dealer_id", insertable = false, updatable = false)
    private Integer dealerId;
    @Column(name = "name")
    private String name;
    @Column(name = "city")
    private City city;

    @OneToMany(mappedBy = "dealer")
    private List<Car> cars = new ArrayList<>();




}
