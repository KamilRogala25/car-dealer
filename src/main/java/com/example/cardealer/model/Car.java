package com.example.cardealer.model;

import com.example.cardealer.enums.Color;
import com.example.cardealer.enums.Manufacturer;
import com.example.cardealer.enums.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonProperty("manufacturer")
    private Manufacturer manufacturer;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("engineCapacity")
    private Double engineCapacity;

    @JsonProperty("model")
    private Model model;

    @JsonProperty("color")
    private Color color;

    @JsonProperty("horsePower")
    private Integer horsePower;

    @JsonProperty("hasTurbo")
    private Boolean hasTurbo;

    @ManyToOne
    @JsonProperty("dealer")
    @JoinColumn(name = "dealer_id", insertable = false, updatable = false)
    private Dealer dealer;



}
